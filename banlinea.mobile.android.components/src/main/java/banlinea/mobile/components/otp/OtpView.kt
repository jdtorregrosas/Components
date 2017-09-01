package banlinea.mobile.components.otp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.AppCompatEditText
import android.telephony.SmsMessage
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import banlinea.mobile.components.R
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


/**
 * Created by jdtor on 28.08.2017
 * OtpView Component
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
    private var isSmsEnabled = false
    private var smsKeyWord = ""

    private var smsReceiver = object : BroadcastReceiver(){
        override fun onReceive(ctx: Context?, i: Intent?) {
            if (i?.action.equals("android.provider.Telephony.SMS_RECEIVED") && isSmsEnabled) {
                val bundle = i?.extras           //---get the SMS message passed in---
                if (bundle != null) {
                    //---retrieve the SMS message received---
                    try {
                        val pdus = bundle.get("pdus") as Array<*>
                        (0..pdus.size).forEach { i ->
                            val msg = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                            val msgBody = msg.messageBody
                            setCodeFromMessageWithKeyword(msgBody, smsKeyWord)
                        }
                    } catch (e: Exception) {
                        //                            Log.d("Exception caught",e.getMessage());
                    }

                }
            }
        }
    }
    private var receiverRegistered = false

    private fun setCodeFromMessageWithKeyword(msgBody:String, keyWord: String){
        if(msgBody.toLowerCase().contains(keyWord.toLowerCase())){
            val splittedBody = msgBody.split(" ")
            splittedBody.forEach { word ->
                if(word.matches(Regex("[0-9]+"))){
                    val splittedWord = word.split("")
                    (0..size).forEach {
                        index -> mTexts[index].text = SpannableStringBuilder(splittedWord[index+1])
                    }
                }
            }
        }
    }
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
            textSize = typedArray.getDimensionPixelSize(R.styleable.otp_attributes_textSize, 32)
            spaceBetween = typedArray.getDimensionPixelSize(R.styleable.otp_attributes_spaceBetween, 32)
            isSmsEnabled = typedArray.getBoolean(R.styleable.otp_attributes_enableSms, false)
            smsKeyWord = typedArray.getString(R.styleable.otp_attributes_smsKeyWord) ?: ""
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
                val tintColorStateList : ColorStateList = ColorStateList.valueOf(tintColor)
                text.supportBackgroundTintList = tintColorStateList
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            }
        }
    }

    fun registerReceiver(ctx: Context){
        val filter = IntentFilter()
        filter.addAction("android.provider.Telephony.SMS_RECEIVED")
        filter.priority =  Int.MAX_VALUE
        ctx.registerReceiver(smsReceiver, filter)
        receiverRegistered = true
    }

    fun unregisterReceiver(ctx: Context){
        if (receiverRegistered) {
            ctx.unregisterReceiver(smsReceiver)
            receiverRegistered = false
        }
    }


    /**
     * Function to creates fields and add them to the linear layout, additionally adds each field into a list.
     * It must be called before any other operation depending on the list mTexts
     */
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
     * Function to focus next/previous field depending on the key pressed event additionally closes soft keyboard
     */
    private fun createFocusActions(){
        for(text:AppCompatEditText in mTexts){
            text.setOnKeyListener { _, keyCode, event ->
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
                false
            }
        }
    }

    /**
     * Helper function to close the soft keyboard within the current context
     */
    private fun closeKeyboard(){
        val view = this
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Function to retrieve the code from the fields
     *
     * @return The code as string
     */
    fun getCode() : String{
        var code = ""
        for(text:AppCompatEditText in mTexts){
            code += text.text
        }
        closeKeyboard()
        return code
    }
}