package ilyos.app.examplemvvm.ui.activities

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseActivity
import ilyos.app.examplemvvm.base.BaseViewModel
import ilyos.app.examplemvvm.base.initialFragment
import ilyos.app.examplemvvm.base.setLayoutContainer
import ilyos.app.examplemvvm.ui.screens.authorization.AuthViewModel
import ilyos.app.examplemvvm.ui.screens.authorization.screens.SplashScreen
import ilyos.app.examplemvvm.utils.preferences.SharedManager
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onActivityCreated() {
        viewModel2.repository.show()
        setLayoutContainer(R.id.container)
        initialFragment(SplashScreen())


    }

    @Inject
    lateinit var sharedManager: SharedManager
    @Inject
    lateinit var viewModel: BaseViewModel

    @Inject
    lateinit var viewModel2: AuthViewModel
}

