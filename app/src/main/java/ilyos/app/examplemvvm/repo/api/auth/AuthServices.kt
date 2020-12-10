package ilyos.app.examplemvvm.repo.api.auth

import ilyos.app.examplemvvm.repo.api.oauth2.OauthToken
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthServices {

    @POST("/oauth/token")
    fun getAuth(@Body request: RequestBody): Single<OauthToken>

    @POST("/v1/users")
    fun createUser(@Body register: Register): Single<Register>

    @POST("/v1/users/{email}/forgot-password")
    fun forgotPassword(@Path("email") email : String) : Single<ResponseBody>

    @POST("/v1/users/reset-token/{token}")
    fun resetPassword(@Path("token") token: String, @Body reset : ResetPass) : Single<Register>

    @POST("v1/users/{id}/send-confirmation-email")
    fun resendConfirmation(@Path("id") id: Int) : Observable<Response<Void>>


    data class Token(val token: String)

    data class Register(
        val id: Int? = null,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val timezone: String? = null
    )

    data class ResetPass(val password: String, val passwordRepeat : String)

    data class ErrorResponse(val apierror: ApiError)
    data class SignInResponse(val error_description: String, val message: String)
    data class ApiError(val message : String)



}