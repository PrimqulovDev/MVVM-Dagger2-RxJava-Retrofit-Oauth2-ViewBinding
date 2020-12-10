package ilyos.app.examplemvvm.repo.api.oauth2

import com.google.gson.annotations.SerializedName
import ilyos.app.examplemvvm.utils.Constants.Companion.ACCESS_TOKEN
import ilyos.app.examplemvvm.utils.Constants.Companion.CONTEXT
import ilyos.app.examplemvvm.utils.Constants.Companion.REFRESH_TOKEN
import ilyos.app.examplemvvm.utils.Constants.Companion.TOKEN_TYPE
import java.io.Serializable

class OauthToken() : Serializable {
    @SerializedName(TOKEN_TYPE)
    private var tokenType: String? = null

    @SerializedName(REFRESH_TOKEN)
    private var refreshToken: String? = null

    @SerializedName(ACCESS_TOKEN)
    private var accessToken: String? = null

    @SerializedName(CONTEXT)
    private var context: AuthContext? = null


    fun clone(oauthToken: OauthToken) {
        if (oauthToken.getRefreshToken() != null)
            setRefreshToken(oauthToken.getRefreshToken()!!)
        else setRefreshToken(null)
        if (oauthToken.getAccessToken() != null)
            setAccessToken(oauthToken.getAccessToken()!!)
        else setAccessToken(null)
        if (oauthToken.getTokenType() != null)
            setTokenType(oauthToken.getTokenType()!!)
        else setTokenType(null)
        context!!.clone(oauthToken.getAuthContext()!!)
    }

    fun getAccessToken(): String? {
        return accessToken
    }

    fun setAccessToken(accessToken: String?) {
        this.accessToken = accessToken
    }

    fun getRefreshToken(): String? {
        return refreshToken
    }

    fun setRefreshToken(refreshToken: String?) {
        this.refreshToken = refreshToken
    }

    fun getTokenType(): String? {
        return tokenType
    }

    fun setTokenType(tokenType: String?) {
        this.tokenType = tokenType
    }

    fun setAuthContext(authContext: AuthContext?) {
        this.context = authContext
    }

    fun getAuthContext(): AuthContext? {
        return context
    }

    fun bearer(): String {
        return String.format("Bearer %s", this.getAccessToken())
    }
}