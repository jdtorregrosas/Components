package com.jdtorregrosas.components.questionsList

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsList.models.Answer

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
    private var errorMessage = ""
    private var mandatoryAnswers = false
    private var questions = listOf<Question>()
    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.QuestionsListView, 0, 0)
            showNextAfterCompletion = typedArray.getBoolean(R.styleable.QuestionsListView_showNextAfterCompletion, false)
            mandatoryAnswers = typedArray.getBoolean(R.styleable.QuestionsListView_mandatoryAnswers, false)
            errorMessage = typedArray.getString(R.styleable.QuestionsListView_errorMessage) ?: ""
            typedArray.recycle()
        }
    }
    fun setQuestions(questions : List<Question>){
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(40,40,40,40)
        if(mandatoryAnswers){
            for(i in 0 until questions.size){
                questions[i].isMandatory = true
                if(!errorMessage.isNullOrBlank()){
                    questions[i].errorMessage = errorMessage
                }
            }
        }
        for(i in 0 until questions.size){
            questions[i].createQuestion()
            if(showNextAfterCompletion && i!=0) questions[i].getView().visibility = View.GONE
            linearLayout.addView(questions[i].getView())

        }
        if(showNextAfterCompletion) setOnAnsweredListeners(questions)
        this.addView(linearLayout)
        this.questions = questions
    }

    private fun showNextQuestion(questions: List<Question>, question: Question){
        (0 until questions.size-1)
                .filter { questions[it] === question }
                .forEach { questions[it +1].getView().visibility = View.VISIBLE }
        this.post{ this.scrollTo(0, this.bottom) }
    }

    private fun setOnAnsweredListeners(questions: List<Question>){
        for(question in questions){
            question.setOnAnsweredListener { showNextQuestion(questions, question) }
        }
    }

    fun getAnswers() : List<Answer>{
        var answers = mutableListOf<Answer>()
        if(questions.isNotEmpty()){
            for(question in questions){
                if(question.isAnswered()){
                    answers.add(question.getAnswer()!!)
                } else {
                    answers.add(Answer(-1, ""))
                }
            }
        }
        return answers
    }
}