package ilyos.app.examplemvvm.ui.screens.profile

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.databinding.ScreenProfileBinding
import javax.inject.Inject

class ProfileScreen : BaseFragment(R.layout.screen_profile) {

    private var _binding: ScreenProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: ProfileViewModel

    override fun initialize() {
        _binding = ScreenProfileBinding.bind(requireView())


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}