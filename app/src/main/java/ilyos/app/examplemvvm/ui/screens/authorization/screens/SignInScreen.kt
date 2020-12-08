package ilyos.app.examplemvvm.ui.screens.authorization.screens

import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.databinding.ScreenSignInBinding
import ilyos.app.examplemvvm.ui.screens.authorization.AuthViewModel
import ilyos.app.examplemvvm.ui.screens.guest.GuestScreen
import ilyos.app.examplemvvm.ui.screens.home.HomeScreen
import ilyos.app.examplemvvm.utils.extensions.isValidForEmail
import ilyos.app.examplemvvm.utils.extensions.isValidForPassword
import javax.inject.Inject

class SignInScreen : BaseFragment(R.layout.screen_sign_in) {

    private var _binding: ScreenSignInBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: AuthViewModel

    override fun initialize() {
        _binding = ScreenSignInBinding.bind(requireView())

        binding.buttonGuest.setOnClickListener {
            replaceFragment(GuestScreen())
        }
        binding.buttonForgotPassword.setOnClickListener { addFragment(ResetPasswordScreen()) }
        binding.imageBack.setOnClickListener { finishFragment() }
        binding.buttonLogin.setOnClickListener {
            if (checkFields()) {
                val username = binding.inputEmail.text.toString()
                val password = binding.inputPassword.text.toString()
                viewModel.getAuth(username, password)
            }
        }

        viewModel.oauthToken.observe(viewLifecycleOwner, {
            if (it != null && it.getAuthContext()?.user != null) {
                /*Open Main Screen*/
                replaceFragment(HomeScreen())
            }
        })

    }

    private fun checkFields(): Boolean {
        val emptyError = "Field is required"
        with(binding) {
            if (inputEmail.text.toString().isEmpty()) {
                inputEmail.errorMess = emptyError
                return false
            }

            if (!inputEmail.isValidForEmail()) {
                inputEmail.errorMess = getString(R.string.emailValidationError)
                return false
            }

            if (inputPassword.text.toString().isEmpty()) {
                inputPassword.errorMess = emptyError
                return false
            }

            if (!inputPassword.isValidForPassword()) {
                inputPassword.errorMess = getString(R.string.passwordValidationError)
                return false
            }
        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}