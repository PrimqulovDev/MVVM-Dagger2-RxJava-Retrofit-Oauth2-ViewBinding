package ilyos.app.examplemvvm.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.commit
import dagger.android.support.DaggerFragment
import ilyos.app.examplemvvm.R

/**
 * Developed by Ilyos
 */

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : DaggerFragment() {

    @IdRes
    var parentLayId: Int = R.id.container

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        parentLayId = viewModels<BaseViewModel>(ownerProducer = {requireActivity()}).value.parentLayoutId
        initialize()
    }

    fun addFragment(fragment: BaseFragment,
        addBackStack: Boolean = true, @IdRes id: Int = parentLayId
    ) {
        requireActivity().supportFragmentManager.commit {
            if (addBackStack) addToBackStack(fragment.hashCode().toString())
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            add(id, fragment)
        }
    }

    fun replaceFragment(fragment: BaseFragment, @IdRes id: Int = parentLayId) {
        requireActivity().supportFragmentManager.commit {
            replace(id, fragment)
        }
    }

    fun finishFragment() {
        requireActivity().supportFragmentManager.popBackStackImmediate()
    }

    fun addFragmentWithAnim(fragment: BaseFragment, @IdRes id: Int = parentLayId) {
        hideKeyboard()
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            add(id, fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
    }

    protected fun hideKeyboard() {
        val manager: InputMethodManager =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null)
            manager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    abstract fun initialize()
}