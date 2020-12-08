package ilyos.app.examplemvvm.base

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import ilyos.app.examplemvvm.R
import ilyos.app.examplemvvm.utils.extensions.logd

/**
 * Developed by Ilyos
 */

abstract class BaseActivity(@LayoutRes private val layoutId: Int) : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onActivityCreated()
    }

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 0 -> finishFragment()
            supportFragmentManager.backStackEntryCount == 0 -> exitVariant()
            else -> super.onBackPressed()
        }
    }

    abstract fun onActivityCreated()
}


var exit = false
fun AppCompatActivity.exitVariant() {
    if (exit) {
        finishAffinity()
    } else {
        Toast.makeText(this, this.getString(R.string.back_again), Toast.LENGTH_SHORT).show()
        exit = true
        Handler().postDelayed({ exit = false }, 2000)
    }
}

fun AppCompatActivity.setLayoutContainer(@IdRes resId: Int) {
    viewModels<BaseViewModel>().value.parentLayoutId = resId
//    ViewModelProviders.of(this)[BaseViewModel::class.java].parentLayoutId = resId
}

fun AppCompatActivity.initialFragment(fragment: BaseFragment) {
    logd("Initial Fragment")
    val containerId = viewModels<BaseViewModel>().value.parentLayoutId
    supportFragmentManager.beginTransaction().add(containerId, fragment).commit()
}

fun AppCompatActivity.finishFragment() {
    supportFragmentManager.popBackStack()
}
