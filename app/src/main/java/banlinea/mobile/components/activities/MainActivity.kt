package banlinea.mobile.components.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import banlinea.mobile.components.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by jdtor on 28.08.2017 for components.
 */
class MainActivity : AppCompatActivity() {

    val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainOtpButton.setOnClickListener{
            _ -> navigateToOtp()
        }
        mainQuestionsListButton.setOnClickListener{
            _ -> navigateToQuestionsList()
        }
    }

    private fun navigateToOtp(){
        val i = Intent(mContext, OtpActivity::class.java)
        startActivity(i)
    }

    private fun navigateToQuestionsList(){
        val i = Intent(mContext, QuestionsListActivity::class.java)
        startActivity(i)
    }
}