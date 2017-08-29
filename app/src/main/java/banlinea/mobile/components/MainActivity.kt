package banlinea.mobile.components

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by jdtor on 28.08.2017 for components.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainOtpButton.setOnClickListener{
            view -> navigateToOtp()
        }
    }

    fun navigateToOtp(){
        val i = Intent(this, OtpActivity::class.java)
        startActivity(i)
    }
}