package com.esia.timetable


import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
//import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_web_view.*
import java.text.SimpleDateFormat
import java.util.*

class webViewActivity : AppCompatActivity() {
    var noBack: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
//        val sharedPreference: SharedPreference = SharedPreference(this)
//
//        noBack = sharedPreference.getBValueBoolean("state",true) == true
        supportActionBar?.hide()
//        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)


        val myurl = intent.getStringExtra("urltoload")
        webView.loadUrl(myurl!!)

        noBack = intent.getBooleanExtra("state", false)
//        sharedPreference.bsave("state",true)
        while (noBack == true) {
            onBackPressed()
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        val simpleDateFormat = SimpleDateFormat("HH:mm:ss z")
//        val currentDateAndTime: String = simpleDateFormat.format(Date())
//        if (currentDateAndTime == "09:15:00") {
//            Alerter.create(this)
//                .setTitle("Timetable")
//                .setText("Your class is about to start")
//                .setIcon(R.drawable.applogo)
//                .setBackgroundColorRes(R.color.teal_200)
//                .show()
//        }

//        swipeRefreshLayout.setOnRefreshListener {
//
//            // Your code goes here
//            // In this code, we are just changing the text in the
//            // textbox
//            webView.reload()
//
//            // This line is important as it explicitly refreshes only once
//            // If "true" it implicitly refreshes forever
//            swipeRefreshLayout.isRefreshing = false
//        }
        refreshBtn.setOnClickListener {
            webView.reload()
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}