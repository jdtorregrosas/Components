package com.jdtorregrosas.components.questionsCarousel

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsCarousel.models.CarouselAnswer

/**
 * Created by jdtor on 25.09.2017 for components.
 * Item Question
 */
class CarouselItemQuestion(context: Context, private val label: String,
                           private val answers: MutableList<CarouselAnswer>,
                           private val attrs: AttributeSet? = null ) : ScrollView(context, attrs){

    private var clickListener : () -> Unit = {}
    private var selectedAnswer  = ""
    private var buttons = mutableListOf<Button>()
    private var buttonColor : Int = Color.GRAY
    private var buttonTextColor : Int = Color.GRAY
    private var labelColor : Int = Color.GRAY

    init{}

    fun createQuestion(){
        this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        this.isFillViewport = true
        this.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER

        linearLayout.setPadding(0,15,0,15)
        val questionLabel = AppCompatTextView(context)
        questionLabel.setTextColor(labelColor)
        questionLabel.text = SpannableString(label)
        linearLayout.addView(questionLabel)

        answers.forEach {
            val answerButton = Button(context)
            answerButton.text = it.label
            selectedAnswer = it.label
            attrs?.let {
                val typedArray = context.obtainStyledAttributes(it, R.styleable.CarouselItemQuestion, 0, 0)
                buttonColor = typedArray.getColor(R.styleable.CarouselItemQuestion_buttonItemColor, Color.GRAY)
                buttonTextColor = typedArray.getColor(R.styleable.CarouselItemQuestion_buttonItemTextColor, Color.GRAY)
                typedArray.recycle()
            }
            answerButton.setBackgroundColor(buttonColor)
            val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            buttonParams.setMargins(20,20,20,20)
            answerButton.layoutParams =  buttonParams
            answerButton.setTextColor(buttonTextColor)
            answerButton.setAllCaps(false)
            answerButton.setOnClickListener{ button ->
                val buttonBackground = button.background

                if(buttonBackground is ColorDrawable){
                    buttons.forEach {
                        if(it == button){
                            it.setBackgroundColor(Color.argb(90, Color.red(buttonColor), Color.green(buttonColor), Color.blue(buttonColor)))
                            it.setTextColor(Color.argb(95, Color.red(buttonTextColor), Color.green(buttonTextColor), Color.blue(buttonTextColor)))
                        } else {
                            it.setBackgroundColor(buttonColor)
                            it.setTextColor(buttonTextColor)
                        }
                    }
                }
                clickListener()
                selectedAnswer = it.value

            }
            selectedAnswer = ""
            buttons.add(answerButton)
            linearLayout.addView(answerButton)
        }
        this.addView(linearLayout)
    }

    fun getSelectedAnswer(): String{
        return selectedAnswer
    }

    fun setOnClickListener(listener: () -> Unit){
        clickListener = listener
    }

    fun setButtonColor(color: Int){
        buttonColor = color
    }
    fun setButtonTextColor(color: Int){
        buttonTextColor = color
    }

    fun setLabelColor(color: Int){
        labelColor = color
    }
}

