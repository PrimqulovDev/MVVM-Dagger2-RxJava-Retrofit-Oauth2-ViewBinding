package ilyos.app.examplemvvm.utils.extensions

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import ilyos.app.examplemvvm.utils.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

private var toast: Toast? = null
fun toast(context: Context, message: String) {
    toast?.apply { cancel() }
    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT).apply { show() }
}

fun toastLong(context: Context, message: String) {
    toast?.apply { cancel() }
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG).apply { show() }
}

fun snackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun snackbarLong(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun inDevelopment(context: Context) {
    toast(context, "In Development")
}

fun toastClicked(context: Context, msg: String) {
    toast(context, "$msg Clicked")
}

fun loge(message: String, tag: String = Constants.TAG) {
    Log.e(tag, message)
}

fun loge(clazz: Any) {
    Log.e("RRR", Gson().toJson(clazz))
}

fun logw(message: String, tag: String = Constants.TAG) {
    Log.w(tag, message)
}

fun logd(message: String, tag: String = Constants.TAG) {
    Log.d(tag, message)
}

