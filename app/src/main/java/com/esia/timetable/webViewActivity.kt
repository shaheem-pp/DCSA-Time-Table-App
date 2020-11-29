package com.esia.timetable


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class webViewActivity : AppCompatActivity() {
    var noBack:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
//        val sharedPreference: SharedPreference = SharedPreference(this)
//
//        noBack = sharedPreference.getBValueBoolean("state",true) == true
        supportActionBar?.hide()


        val myurl = intent.getStringExtra("urltoload")
        webView.loadUrl(myurl!!)

        noBack = intent.getBooleanExtra("state", false)
//        sharedPreference.bsave("state",true)
        while (noBack == true) {
            onBackPressed()
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

    }

    override fun onBackPressed() {
        finish()
    }
}