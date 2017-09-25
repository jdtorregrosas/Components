package com.jdtorregrosas.components.questionsCarousel

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView

/**
 * Created by jdtor on 25.09.2017 for components.
 */
class CarouselItemQuestion(private val context: Context, private val label: String,  private val answers: MutableList<CarouselAnswer> ){
    private val scrollView = ScrollView(context)
    //private val linearLayout = LinearLayout(context)


    init{
        scrollView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
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
            linearLayout.addView(answerButton)
        }
        scrollView.addView(linearLayout)
    }

    fun getView() : ScrollView{
        return scrollView
    }
}

data class CarouselAnswer(val label: String, val value:String)
