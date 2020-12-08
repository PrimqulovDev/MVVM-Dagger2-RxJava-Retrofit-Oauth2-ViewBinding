package ilyos.app.examplemvvm.ui.screens.home

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.databinding.ScreenHomeBinding
import javax.inject.Inject

class HomeScreen : BaseFragment(R.layout.screen_home) {

    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun initialize() {
        _binding = ScreenHomeBinding.bind(requireView())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}