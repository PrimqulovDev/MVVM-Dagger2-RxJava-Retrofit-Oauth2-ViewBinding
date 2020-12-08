package ilyos.app.examplemvvm.repo

import ilyos.app.examplemvvm.repo.remote.AuthServices
import ilyos.app.examplemvvm.repo.remote.oauth2.OauthToken
import ilyos.app.examplemvvm.utils.Constants
import ilyos.app.examplemvvm.utils.preferences.SharedManager
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.FormBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authServices: AuthServices,
    private val sharedManager: SharedManager
) {

    fun getAuth(): Single<OauthToken> {
        return authServices.getAuth(
            FormBody.Builder()
                .add(Constants.GRANT_TYPE, "client_credentials")
                .build()
        ).observeAndSubscribe()
    }

    fun getAuth(username: String, password: String): Single<OauthToken> {
        return authServices.getAuth(
            FormBody.Builder()
                .add(Constants.GRANT_TYPE, Constants.PASSWORD)
                .add(Constants.USER_NAME, username)
                .add(Constants.PASSWORD, password)
                .build()
        ).observeAndSubscribe()
    }

    fun createUser(register: AuthServices.Register): Single<AuthServices.Register> {
        return authServices.createUser(register).observeAndSubscribe()
    }

    fun forgotPassword(email: String): Single<ResponseBody> =
        authServices.forgotPassword(email).observeAndSubscribe()

    fun resetPassword(token: String, password: String): Single<AuthServices.Register> =
        authServices.resetPassword(token, AuthServices.ResetPass(password, password))
            .observeAndSubscribe()

    fun resendConfirmation(id: Int): Observable<Response<Void>> =
        authServices.resendConfirmation(id).observeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

    private fun <T> Single<T>.observeAndSubscribe() =
        subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

}