package banlinea.mobile.components

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp.*

class OtpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        otpShowCode.setOnClickListener{
            view -> showCode()
        }

    }

    private fun showCode(){
        otpCode.text = otpView.getCode()
    }
}
