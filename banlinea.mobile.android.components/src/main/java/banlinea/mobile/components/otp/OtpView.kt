package banlinea.mobile.components.otp

import android.content.Context
import android.provider.Contacts
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.LinearLayout
import android.widget.TextView
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
        var total: Int = 4
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.otp_attributes, 0, 0)
            total = typedArray.getInt(R.styleable.otp_attributes_size, 4)
            typedArray.recycle()
        }
        val hint: String = "*"
        var i = 0
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
            text.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    if (p0?.length == 1) {
                        val nextText = mTexts.getOrNull(mTexts.indexOf(text)+1)
                        nextText?.requestFocus()
                    } else if (p0?.length == 0) {
                        val nextText = mTexts.getOrNull(mTexts.indexOf(text)-1)
                        nextText?.requestFocus()
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL
    }
}