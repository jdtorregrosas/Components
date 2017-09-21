package com.jdtorregrosas.components.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import banlinea.mobile.components.R
import com.jdtorregrosas.components.questionsList.Question
import com.jdtorregrosas.components.questionsList.models.Answer
import com.jdtorregrosas.components.questionsList.models.QuestionType
import kotlinx.android.synthetic.main.activity_questions_list.*
import kotlinx.android.synthetic.main.toolbar.*

class QuestionsListActivity : AppCompatActivity() {

    private val mContext : Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_list)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Questions List Component"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{ _ -> onBackPressed()}
        val questions = mutableListOf<Question>()
        questions.add(Question(mContext, QuestionType.OPEN, listOf("Are you feeling better today?")))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("What colour shirt are you wearing?"),
                mutableListOf(Answer(1, "Red"), Answer(2, "Green"), Answer(3, "Blue")), "select a color"))
        questions.add(Question(mContext, QuestionType.OPEN, listOf("Do you like this?")))
        questions.add(Question(mContext, QuestionType.OPEN, listOf("Do you get on well with your boss?")))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Where do you live?"),
                mutableListOf(Answer(1, "Bogotá"), Answer(2, "Berlin"))))
        questions.add(Question(mContext, QuestionType.OPEN, listOf("Who will you vote for this election?")))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Which food do you prefer?"),
                mutableListOf(Answer(1, "Tomatoes"), Answer(2, "Pasta"), Answer(3, "Tuna"), Answer(2, "Fried chips"))))
        questionsListView.setQuestions(questions)

        questionsListViewButtonLog.setOnClickListener{
            _ -> questionsListView.getAnswers().map {
                println("${it.number} ${it.value}")
            }
        }
    }
}
