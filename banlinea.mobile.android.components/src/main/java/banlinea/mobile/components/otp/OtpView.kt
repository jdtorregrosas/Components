package banlinea.mobile.components.otp

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import banlinea.mobile.components.R

/**
 * Created by jdtor on 28.08.2017 for components.
 */
class OtpView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    val mTexts : MutableList<EditText> = mutableListOf()

    init {
        var hint: String = "*"
        var total: Int = 4
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.otp_attributes, 0, 0)
            total = typedArray.getInt(R.styleable.otp_attributes_size, 4)
            hint = typedArray.getString(R.styleable.otp_attributes_hint)
            typedArray.recycle()
        }

        var i : Int = 0
        while(i < total){
            val text = EditText(context)
            addView(text)
            text.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f)
            text.textAlignment = View.TEXT_ALIGNMENT_CENTER
            text.inputType = InputType.TYPE_CLASS_NUMBER
            text.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
            text.hint = hint
            mTexts.add(text)
            i++
        }
        for(text:EditText in mTexts){
            text.setOnKeyListener(object: OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    if(event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL){
                        val nextText = mTexts.getOrNull(mTexts.indexOf(text)-1)
                        nextText?.requestFocus()
                    } else if(event?.action == KeyEvent.ACTION_DOWN) {
                        val nextText = mTexts.getOrNull(mTexts.indexOf(text)+1)
                        nextText?.requestFocus()
                    }
                    return false
                }
            })
        }
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL
    }
}