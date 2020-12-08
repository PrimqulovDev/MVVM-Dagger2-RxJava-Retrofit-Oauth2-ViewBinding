package ilyos.app.examplemvvm.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ilyos.app.examplemvvm.ui.screens.authorization.screens.ResetPasswordScreen
import ilyos.app.examplemvvm.ui.screens.authorization.screens.SignInScreen
import ilyos.app.examplemvvm.ui.screens.authorization.screens.SignUpScreen
import ilyos.app.examplemvvm.ui.screens.authorization.screens.SplashScreen
import ilyos.app.examplemvvm.ui.screens.faq.FAQScreen
import ilyos.app.examplemvvm.ui.screens.guest.GuestScreen
import ilyos.app.examplemvvm.ui.screens.home.HomeScreen
import ilyos.app.examplemvvm.ui.screens.profile.ProfileScreen

@Module
internal abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainScreenInjector(): HomeScreen

    @ContributesAndroidInjector
    internal abstract fun contributeSignInScreenInjector(): SignInScreen

    @ContributesAndroidInjector
    internal abstract fun contributeSignUpScreenInjector(): SignUpScreen

    @ContributesAndroidInjector
    internal abstract fun contributeSplashScreenInjector(): SplashScreen

    @ContributesAndroidInjector
    internal abstract fun contributeResetPasswordScreenInjector(): ResetPasswordScreen

    @ContributesAndroidInjector
    internal abstract fun contributeFAQScreenInjector(): FAQScreen

    @ContributesAndroidInjector
    internal abstract fun contributeProfileScreenInjector(): ProfileScreen

    @ContributesAndroidInjector
    internal abstract fun contributeGuestScreenInjector(): GuestScreen

}