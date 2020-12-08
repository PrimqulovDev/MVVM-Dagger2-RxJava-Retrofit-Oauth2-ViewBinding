package ilyos.app.examplemvvm.ui.screens.guest

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.ui.screens.authorization.screens.SplashScreen
import ilyos.app.examplemvvm.utils.custom_views.CustomButton

class GuestScreen : BaseFragment(R.layout.screen_guest) {

    override fun initialize() {

        requireView().findViewById<CustomButton>(R.id.btnSignInUp)
            .setOnClickListener {
                finishFragment()
                replaceFragment(SplashScreen())
            }
    }

}