package banlinea.mobile.components.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import banlinea.mobile.components.R
import kotlinx.android.synthetic.main.toolbar.*

class QuestionsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_list)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Questions List Component"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{ _ -> onBackPressed()}
    }
}
