package ilyos.app.examplemvvm.ui.screens.faq

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.databinding.ScreenFaqBinding
import javax.inject.Inject

class FAQScreen : BaseFragment(R.layout.screen_faq) {

    private var _binding: ScreenFaqBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: FAQViewModel

    override fun initialize() {
        _binding = ScreenFaqBinding.bind(requireView())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}