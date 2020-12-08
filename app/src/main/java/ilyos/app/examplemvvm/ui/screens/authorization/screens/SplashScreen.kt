package ilyos.app.examplemvvm.ui.screens.authorization.screens

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.utils.custom_views.CustomButton

class SplashScreen : BaseFragment(R.layout.screen_splash) {

    override fun initialize() {
        requireView().findViewById<CustomButton>(R.id.buttonSignIn).setOnClickListener {
            addFragment(SignInScreen())
        }
        requireView().findViewById<CustomButton>(R.id.buttonSignUp).setOnClickListener {
            addFragment(SignUpScreen())
        }
    }



}