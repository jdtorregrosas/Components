package com.jdtorregrosas.components.questionsList

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import banlinea.mobile.components.R

/**
 * Created by jdtor on 09.09.2017 for components.
 */
class QuestionsListView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : ScrollView(context, attrs, defStyle, defStyleRes) {

    private var showNextAfterCompletion = false
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.QuestionsListView, 0, 0)
            showNextAfterCompletion = typedArray.getBoolean(R.styleable.QuestionsListView_showNextAfterCompletion, false)
            typedArray.recycle()
        }
    }
    fun setQuestions(questions : List<Question>){
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(40,40,40,40)
        for(i in 0 until questions.size){
            linearLayout.addView(questions[i].getView())
            if(showNextAfterCompletion && i!=0) questions[i].getView().visibility = View.GONE
        }
        if(showNextAfterCompletion) setOnAnsweredListeners(questions)
        this.addView(linearLayout)
    }

    private fun showNextQuestion(questions: List<Question>, question: Question){
        (0 until questions.size-1)
                .filter { questions[it] === question }
                .forEach { questions[it +1].getView().visibility = View.VISIBLE }
    }

    private fun setOnAnsweredListeners(questions: List<Question>){
        for(question in questions){
            question.setOnAnsweredListener { showNextQuestion(questions, question) }
        }
    }
}