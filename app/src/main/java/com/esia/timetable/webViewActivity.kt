package com.esia.timetable


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class webViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        supportActionBar?.hide()
//        val j = Intent(applicationContext, webViewActivity::class.java)
//        j.putExtra("urltoload", url)
//        j.putExtra("syllabusPage", true)
//        startActivity(j)

        val myurl = intent.getStringExtra("urltoload")
        webView.loadUrl(myurl!!)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        println(myurl)
    }
}