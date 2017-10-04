package com.jdtorregrosas.components.activities

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toolbar
import com.jdtorregrosas.components.R
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert

class OtpActivity : AppCompatActivity() {

    private val REQUEST_SMS_RECEIVE = 1550
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Otp Component"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener{ _ -> onBackPressed()}

        otpShowCode.setOnClickListener{
            _ -> showCode()
        }

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), REQUEST_SMS_RECEIVE)    }

    override fun onPause() {
        otpView.unregisterReceiver(this)
        super.onPause()
    }

    override fun onDestroy() {
        otpView.unregisterReceiver(this)
        super.onDestroy()
    }

    private fun showCode(){
        alert("Your code is ${otpView.getCode()}") {  }.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_SMS_RECEIVE) {
            otpView.registerReceiver(this)
        }
    }
}
