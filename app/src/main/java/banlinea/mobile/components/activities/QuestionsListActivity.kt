package banlinea.mobile.components.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import banlinea.mobile.components.R
import banlinea.mobile.components.questionsList.Question
import banlinea.mobile.components.questionsList.models.Answer
import banlinea.mobile.components.questionsList.models.QuestionType
import kotlinx.android.synthetic.main.activity_questions_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert

class QuestionsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_list)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Questions List Component"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{ _ -> onBackPressed()}
        val question1 = Question(this, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf<Answer>(Answer(1, "Pogotá"), Answer(2, "Metrellín")))
        testFrame.addView(question1.getView())
        testFrame.addView(Question(this, QuestionType.OPEN, listOf("Cuál es tu fruta ciudad 2")).getView())
        testFrame.addView(Question(this, QuestionType.OPEN, listOf("Cuál es tu edad refrutera?")).getView())
        testFrame.addView(Question(this, QuestionType.CLOSED, listOf("Cuál es tu fruta ciudad 2"),
                listOf<Answer>(Answer(1, "Pogotá"), Answer(2, "Metrellín"))).getView())

        testButton.setOnClickListener{_ -> alert(question1.getAnswer().value){}.show()}
    }
}
