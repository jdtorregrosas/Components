package banlinea.mobile.components.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import banlinea.mobile.components.R
import banlinea.mobile.components.questionsList.Question
import banlinea.mobile.components.questionsList.models.Answer
import banlinea.mobile.components.questionsList.models.QuestionType
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
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("De qué color son los elefantes"),
                listOf(Answer(1, "Rosados"), Answer(2, "Verdes"), Answer(3, "Amarillos"))))
        questions.add(Question(mContext, QuestionType.OPEN, listOf("Cuál es tu fruta ciudad 2")))
        questions.add(Question(mContext, QuestionType.OPEN, listOf("Cuál es tu edad refrutera?")))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))
        questions.add(Question(mContext, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf(Answer(1, "Pogotá"), Answer(2, "Metrellín"))))

        questionsListView.setQuestions(questions)
    }
}
