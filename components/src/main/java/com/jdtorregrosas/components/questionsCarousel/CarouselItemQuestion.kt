package com.jdtorregrosas.components.questionsCarousel

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.util.AttributeSet
import android.view.Gravity
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

    init{}

    fun createQuestion(){
        this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER

        linearLayout.setPadding(0,15,0,15)
        val questionLabel = AppCompatTextView(context)
        questionLabel.text = SpannableString(label)
        linearLayout.addView(questionLabel)

        answers.forEach {
            val answerButton = Button(context)
            answerButton.text = it.label
            selectedAnswer = it.label
            attrs?.let {
                val typedArray = context.obtainStyledAttributes(it, R.styleable.CarouselItemQuestion, 0, 0)
                buttonColor = typedArray.getColor(R.styleable.CarouselItemQuestion_buttonItemColor, Color.GRAY)
                typedArray.recycle()
            }
            answerButton.setBackgroundColor(buttonColor)
            val buttonParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            buttonParams.setMargins(20,20,20,20)
            answerButton.layoutParams =  buttonParams
            answerButton.setOnClickListener{ button ->
                val buttonBackground = button.background

                if(buttonBackground is ColorDrawable){
                    buttons.forEach {
                        if(it == button){
                            val color = buttonBackground.color
                            it.setBackgroundColor(Color.argb(80, Color.red(color), Color.green(color), Color.blue(color)))
                        } else {
                            it.setBackgroundColor(buttonColor)
                        }
                    }
                }
                clickListener()

            }
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
}

