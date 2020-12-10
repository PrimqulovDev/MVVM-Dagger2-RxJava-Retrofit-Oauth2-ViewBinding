package ilyos.app.examplemvvm.ui.screens.authorization

import androidx.lifecycle.MutableLiveData
import ilyos.app.examplemvvm.base.BaseViewModel
import ilyos.app.examplemvvm.repo.AuthRepository
import ilyos.app.examplemvvm.repo.api.auth.AuthServices.Register
import ilyos.app.examplemvvm.repo.api.oauth2.OauthToken
import ilyos.app.examplemvvm.utils.Errors
import io.reactivex.observers.DisposableSingleObserver
import okhttp3.ResponseBody
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val repository: AuthRepository) : BaseViewModel() {

    val isSendResetLink = MutableLiveData<Boolean>()
    val createUser = MutableLiveData<Register>()

    fun createUser(register: Register) {
        disposable.add(
            repository.createUser(register)
                .subscribeWith(object : DisposableSingleObserver<Register>() {
                    override fun onSuccess(t: Register) {
                        sharedManager.saveAttempt(t)
                        createUser.value = t
                    }

                    override fun onError(e: Throwable) {
                        Errors.traceErrors(e, context)
                    }
                })
        )
    }

    fun getAuth(username: String, password: String) {
        disposable.add(
            repository.getAuth(username, password)
                .subscribeWith(object : DisposableSingleObserver<OauthToken>() {
                    override fun onSuccess(t: OauthToken) {
                        sharedManager.saveOauthToken(t)
                        oauthToken.value = t
                    }

                    override fun onError(e: Throwable) {
                        Errors.traceErrors(e, context)
                    }
                })
        )
    }

    fun forgotPassword(email: String) {
        disposable.add(
            repository.forgotPassword(email)
                .subscribeWith(object : DisposableSingleObserver<ResponseBody>() {
                    override fun onSuccess(t: ResponseBody) {
                        isSendResetLink.value = true
                    }

                    override fun onError(e: Throwable) {
                        Errors.traceErrors(e, context)
                    }
                })
        )
    }
}