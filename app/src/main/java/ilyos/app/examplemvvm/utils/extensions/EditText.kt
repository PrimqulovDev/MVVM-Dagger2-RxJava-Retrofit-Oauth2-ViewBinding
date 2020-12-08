package ilyos.app.examplemvvm.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.regex.Pattern

fun EditText.isValidForPassword(): Boolean {
    val s = text.toString()
    val matcher = VALID_SERVER.matcher(text)
    return (s.isNotEmpty() && matcher.find())
}

fun EditText.isValidForCode(): Boolean {
    val s = text.toString()
    return s.isNotEmpty() && s.length == 6
}

private val VALID_EMAIL_ADDRESS_REGEX =
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

private val VALID_PASSWORD_REGEX =
    Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{6,}\$", Pattern.CASE_INSENSITIVE)

private val VALID_PASSWORD_WITHOUT_SPECIAL_SYMBOLS =
    Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}\$", Pattern.CASE_INSENSITIVE)

private val VALID_SERVER =
    Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)")

fun EditText.isValidForEmail(): Boolean {
    val s = text.toString()
    val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(s)
    return s.isNotEmpty() && matcher.find()
}

val EditText.getStringText: String
    get() {
        return text.toString()
    }

fun EditText.isNotEmpty() = getStringText.isNotEmpty()
fun EditText.isEmpty() = getStringText.isEmpty()

fun EditText.addAfterTextChangedListener(listener: (s: Editable?)->Unit) {

    addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    })

}

fun String.toNumber(): Float? {
    var num = 0F
    this.forEach {
        if (it.isDigit()) {
            num = num*10 + (it-'0')
        } else return null
    }
    return num
}