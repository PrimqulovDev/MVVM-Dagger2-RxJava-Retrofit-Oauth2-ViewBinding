package ilyos.app.examplemvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ilyos.app.examplemvvm.base.BaseViewModel
import ilyos.app.examplemvvm.di.utils.ViewModelKey
import ilyos.app.examplemvvm.di.utils.ViewModelProviderFactory
import ilyos.app.examplemvvm.ui.screens.authorization.AuthViewModel
import ilyos.app.examplemvvm.ui.screens.faq.FAQViewModel
import ilyos.app.examplemvvm.ui.screens.home.HomeViewModel
import ilyos.app.examplemvvm.ui.screens.profile.ProfileViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    fun bindBaseViewModel(viewModel: BaseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FAQViewModel::class)
    fun bindFAQViewModel(viewModel: FAQViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}