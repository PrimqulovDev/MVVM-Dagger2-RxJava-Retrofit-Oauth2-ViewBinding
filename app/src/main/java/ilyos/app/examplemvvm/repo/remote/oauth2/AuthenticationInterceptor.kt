package ilyos.app.examplemvvm.repo.remote.oauth2

import android.util.Log
import com.google.gson.GsonBuilder
import ilyos.app.examplemvvm.BuildConfig
import ilyos.app.examplemvvm.utils.Constants
import ilyos.app.examplemvvm.utils.extensions.loge
import ilyos.app.examplemvvm.utils.preferences.SharedManager
import okhttp3.*
import java.io.IOException

class AuthenticationInterceptor(private val sharedManagerUtil: SharedManager) : Interceptor {
    private lateinit var response: Response

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val authToken = sharedManagerUtil.getOauthToken()

        val reqBuilder = requestBuild(
            original, Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
        )

        response = if (authToken.getAccessToken() == null ||
            original.url.toString().contains("oauth/token")
        ) {
            Log.e("TTT", reqBuilder.toString())
            chain.proceed(reqBuilder.build())
        } else {
            chain.proceed(requestBuild(original, authToken.bearer()).build())
        }

        if (response.code == 401) {
            if (authToken.getRefreshToken() != null) {
                val reqBody = FormBody.Builder()
                    .add(Constants.GRANT_TYPE, Constants.REFRESH_TOKEN)
                    .add(Constants.REFRESH_TOKEN, authToken.getRefreshToken()!!)
                    .build()

                val newRequest = requestBuild(
                    Request.Builder().url("${sharedManagerUtil.getBaseUrl()}/oauth/token").build(),
                    Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
                ).post(reqBody).build()

                response.close()

                val newResponse = chain.proceed(newRequest)

                if (newResponse.code == 200) {
                    val oauthToken = GsonBuilder()
                        .create()
                        .fromJson(newResponse.body?.string(), OauthToken::class.java)
                    newResponse.close()

                    sharedManagerUtil.saveOauthToken(oauthToken)

                    return chain.proceed(requestBuild(original, oauthToken.bearer()).build())
                } else
                    loge("Error in Auth Interceptor, code ${newResponse.code}")
            } else {
                val reqBody = FormBody.Builder()
                    .add(Constants.GRANT_TYPE, "client_credentials")
                    .build()

                val newRequest = requestBuild(
                    Request.Builder().url("${sharedManagerUtil.getBaseUrl()}/oauth/token").build(),
                    Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
                ).post(reqBody).build()

                response.close()

                val newResponse = chain.proceed(newRequest)

                if (newResponse.code == 200) {
                    val oauthToken = GsonBuilder().create()
                        .fromJson(newResponse.body!!.string(), OauthToken::class.java)

                    return chain.proceed(requestBuild(original, oauthToken.bearer()).build())

                }
            }
        }

        return response
    }

    private fun requestBuild(request: Request, auth: String?): Request.Builder {
        return request.newBuilder()
            .header("Authorization", auth!!)
            .method(request.method, request.body)
    }
}