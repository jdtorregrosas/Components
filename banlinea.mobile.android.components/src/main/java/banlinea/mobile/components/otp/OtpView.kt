package banlinea.mobile.components.otp

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.AppCompatEditText
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import banlinea.mobile.components.R
import java.lang.reflect.AccessibleObject.setAccessible
import android.widget.TextView
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager


/**
 * Created by jdtor on 28.08.2017 for components.
 */

@SuppressLint("RestrictedApi")
class OtpView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val mTexts : MutableList<AppCompatEditText> = mutableListOf()

    private var hint = "*"
    private var size = 4
    private var textColor : Int = Color.BLACK
    private var hintColor : Int = Color.GRAY
    private var tintColor : Int = Color.MAGENTA
    private var textSize : Int = 0
    private var spaceBetween : Int = 0
    // private lateinit var drawable : Drawable

    init {
        // Create fields and configure layout
        createFields()
        createFocusActions()
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL

        // Set options from attributes
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.otp_attributes, 0, 0)
            size = typedArray.getInt(R.styleable.otp_attributes_size, 4)
            hint = typedArray.getString(R.styleable.otp_attributes_hint) ?: "*"
            textColor = typedArray.getColor(R.styleable.otp_attributes_textColor, Color.BLACK)
            hintColor = typedArray.getColor(R.styleable.otp_attributes_hintColor, Color.GRAY)
            tintColor = typedArray.getColor(R.styleable.otp_attributes_tintColor, Color.MAGENTA)
            // drawable = typedArray.getDrawable(R.styleable.otp_attributes_backgroundDrawable)
            textSize = typedArray.getDimensionPixelSize(R.styleable.otp_attributes_textSize, 32)
            spaceBetween = typedArray.getDimensionPixelSize(R.styleable.otp_attributes_spaceBetween, 32)
            typedArray.recycle()

            for(text:AppCompatEditText in mTexts){
                text.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f)
                text.textAlignment = View.TEXT_ALIGNMENT_CENTER
                text.inputType = InputType.TYPE_CLASS_NUMBER
                text.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(1))
                text.hint = hint
                text.setTextColor(textColor)
                text.setHintTextColor(hintColor)
                val textParams = text.layoutParams as LinearLayout.LayoutParams
                    textParams.marginEnd = spaceBetween/2
                    textParams.marginStart = spaceBetween/2

                /*
                if (drawable != null)
                    text.setBackgroundDrawable(drawable)
                else {*/
                    val tintColorStateList : ColorStateList = ColorStateList.valueOf(tintColor)
                    text.supportBackgroundTintList = tintColorStateList
                //}
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            }
        }
    }
    private fun createFields(){
        var i = 0
        while(i < size){
            val text = AppCompatEditText(context)
            addView(text)
            mTexts.add(text)
            i++
        }
    }

    /**
     * Need mTexts before
     */
    private fun createFocusActions(){
        for(text:AppCompatEditText in mTexts){
            text.setOnKeyListener(object: OnKeyListener{
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    if(event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL){
                        val nextText = mTexts.getOrNull(mTexts.indexOf(text)-1)
                        nextText?.requestFocus()
                    } else if(event?.action == KeyEvent.ACTION_DOWN) {
                        val nextText = mTexts.getOrNull(mTexts.indexOf(text)+1)
                        nextText?.requestFocus()
                        if(text.text.isNotEmpty()){
                            text.text =  SpannableStringBuilder("")
                        }
                    }
                    return false
                }
            })
        }
    }

    private fun closeKeyboard(){
        val view = this
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }
    open fun getCode() : String{
        var code = ""
        for(text:AppCompatEditText in mTexts){
            code += text.text
        }
        closeKeyboard()
        return code
    }
}