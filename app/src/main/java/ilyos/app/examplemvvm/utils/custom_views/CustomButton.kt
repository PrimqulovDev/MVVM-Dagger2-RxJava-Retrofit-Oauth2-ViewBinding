package ilyos.app.examplemvvm.utils.custom_views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class CustomButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        this.setTypeface(null, Typeface.BOLD)
        typeface = Typeface.createFromAsset(
            context.resources.assets, "fonts/opensans-bold.ttf"
        )
    }
}