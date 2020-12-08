package ilyos.app.examplemvvm.ui.screens.authorization.screens

import android.widget.EditText
import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.base.BaseFragment
import ilyos.app.examplemvvm.databinding.ScreenSignUpBinding
import ilyos.app.examplemvvm.repo.remote.AuthServices
import ilyos.app.examplemvvm.ui.screens.authorization.AuthViewModel
import ilyos.app.examplemvvm.utils.extensions.isValidForEmail
import ilyos.app.examplemvvm.utils.extensions.isValidForPassword
import javax.inject.Inject

class SignUpScreen : BaseFragment(R.layout.screen_sign_up) {

    private var _binding: ScreenSignUpBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: AuthViewModel

    override fun initialize() {
        _binding = ScreenSignUpBinding.bind(requireView())
        with(binding) {
            appBar.textTitle.text = ("Sign up")
            appBar.imageBack.setOnClickListener { finishFragment() }
            buttonCreateAccount.setOnClickListener {
                if (checkFields()) {
                    viewModel.createUser(
                        AuthServices.Register(
                            firstName = inputFirstName.text.toString(),
                            lastName = inputLastName.text.toString(),
                            email = inputEmail.text.toString(),
                            password = inputPassword.text.toString()
                        )
                    )
                }
            }

        }
    }

    private fun checkFields(): Boolean {
        val emptyError = "Field is required"
        with(binding) {

            if (inputFirstName.text.toString().isEmpty()) {
                inputFirstName.errorMess = emptyError
                return false
            }

            if (inputLastName.text.toString().isEmpty()) {
                inputLastName.errorMess = emptyError
                return false
            }

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

            if (inputPassword.text.toString() != inputConfirmPassword.text.toString()) {
                inputConfirmPassword.errorMess = "Passwords don't match"
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

var EditText.errorMess: String
    get() = ""
    set(value) {
        requestFocus()
        error = value
    }