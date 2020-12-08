package ilyos.app.examplemvvm.di.modules

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Developed by Ilyos
 */

@Module
open class ViewModelFieldsModule {

    @Provides
    @Singleton
    fun provideMutableLiveData() = MutableLiveData<Any>()

}