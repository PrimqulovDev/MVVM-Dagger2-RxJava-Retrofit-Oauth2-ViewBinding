package ilyos.app.examplemvvm.ui.screens.authorization.screens

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.databinding.ScreenResetPasswordBinding
import ilyos.app.examplemvvm.ui.screens.authorization.AuthViewModel
import ilyos.app.examplemvvm.utils.extensions.isEmpty
import ilyos.app.examplemvvm.utils.extensions.isValidForEmail
import javax.inject.Inject

class ResetPasswordScreen : BaseFragment(R.layout.screen_reset_password) {

    private var _binding: ScreenResetPasswordBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: AuthViewModel

    override fun initialize() {
        _binding = ScreenResetPasswordBinding.bind(requireView())
        binding.appBar.textTitle.text = ("Reset Password")
        binding.appBar.imageBack.setOnClickListener { finishFragment() }
        binding.buttonNext.setOnClickListener {
            with(binding.editTextMail) {
                when {
                    isEmpty() -> {
                        errorMess = ("Field is required")
                    }
                    !isValidForEmail() -> {
                        errorMess = ("Email should be like example@company.com")
                    }
                    else -> {
                        viewModel.forgotPassword(text.toString())
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}