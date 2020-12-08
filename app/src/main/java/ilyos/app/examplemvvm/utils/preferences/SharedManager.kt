package ilyos.app.examplemvvm.utils.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import ilyos.app.examplemvvm.repo.remote.AuthServices.Register
import com.google.gson.reflect.TypeToken
import ilyos.app.examplemvvm.repo.remote.oauth2.OauthToken
import ilyos.app.examplemvvm.BuildConfig
import ilyos.app.examplemvvm.BuildConfig.BASE_URL
import javax.inject.Inject

class SharedManager @Inject constructor(
    private val preferences: SharedPreferences,
    private val gson: Gson
) {
    companion object {
        const val TOKEN = "TOKEN"
        const val USER_NAME = "USER_NAME"
        const val AUTH_TOKEN = "AUTH_TOKEN"
        const val ATTEMPT = "ATTEMPT"

    }

    private val debug = "https://www.uz"
    private val prod = "https://www.uz"

    private fun <T> getData(json: String?): T {
        return gson.fromJson<T>(json, object : TypeToken<T>() {}.type)
    }

    var token: String
        get() = preferences.getString(TOKEN, "")!!
        set(value) {
            preferences.edit { putString(TOKEN, value) }
        }
    var userName: String
        get() = preferences.getString(USER_NAME, "Unknown")!!
        set(value) {
            preferences.edit { putString(USER_NAME, value) }
        }


    fun saveAttempt(register: Register) {
        preferences.edit().putString(ATTEMPT, Gson().toJson(register)).apply()
    }

    fun getAttempt(): Register? {
        val attempt = Gson().fromJson(preferences.getString(ATTEMPT, ""), Register::class.java)

        return attempt
    }

    fun saveOauthToken(oauthToken: OauthToken) {
        preferences.edit()
            .putString(AUTH_TOKEN, gson.toJson(oauthToken))
            .apply()
    }

    fun getOauthToken(): OauthToken {
        var token =
            Gson().fromJson(preferences.getString(AUTH_TOKEN, ""), OauthToken::class.java)
        if (token == null) token = OauthToken()
        return token
    }

    fun getBaseUrl(): String = if (BuildConfig.DEBUG) {
        preferences.getString(BASE_URL, debug) ?: debug
    } else {
        preferences.getString(BASE_URL, prod) ?: prod
    }

    fun deleteAll() {
        preferences.edit().clear().apply()
    }

    fun saveAdmin(admin: Boolean) {
        preferences.edit().putBoolean("ADMIN", admin).apply()
    }

    fun getAdmin() = preferences.getBoolean("ADMIN", false)
}
