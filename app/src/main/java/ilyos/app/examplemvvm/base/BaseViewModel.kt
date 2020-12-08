package ilyos.app.examplemvvm.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ilyos.app.examplemvvm.repo.AuthRepository
import ilyos.app.examplemvvm.repo.remote.oauth2.OauthToken
import ilyos.app.examplemvvm.utils.Errors
import ilyos.app.examplemvvm.utils.preferences.SharedManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Developed by Ilyos
 */

open class BaseViewModel @Inject constructor() : ViewModel() {

    @LayoutRes
    var parentLayoutId: Int = 0

    @Inject
    lateinit var context: Context
    @Inject
    lateinit var sharedManager: SharedManager
    @Inject
    lateinit var authRepository: AuthRepository


    val data = MutableLiveData<Any>()
    val singleData = MutableLiveData<Any>()
    val oauthToken = MutableLiveData<OauthToken>()

    val disposable = CompositeDisposable()

    fun getAuth() {
        disposable.add(
            authRepository.getAuth().subscribeWith(object : DisposableSingleObserver<OauthToken>() {
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




    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}