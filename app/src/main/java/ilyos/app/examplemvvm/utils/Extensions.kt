package ilyos.app.examplemvvm.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

internal fun Char.isPlaceHolder(): Boolean = this == '#'

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Context.toast(msg: String?, dur: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, dur).show()
}

fun String.toDigits(): String = this.replace(Regex("[^\\d.]"), "")


// Try to hide the keyboard and returns whether it worked

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


// Extension method to provide hide keyboard for [Fragment].

fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(
            Context
                .INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}


fun EditText.showSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun Fragment.logoInt(name: String): Int {
    var fileName = name
    if (fileName.endsWith(".png", true))
        fileName = name.dropLast(4)
    var res = 0
    try {
        res = this.resources.getIdentifier(fileName, "drawable", this.context!!.packageName)
    } catch (ex: IllegalStateException) {
        res = -1
    }
    return res
}

fun Date.formatDateStr(pattern: String = "yyyy.MM.dd"): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(this)
}
