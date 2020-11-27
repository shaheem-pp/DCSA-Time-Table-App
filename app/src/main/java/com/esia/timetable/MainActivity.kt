package com.esia.timetable

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //    private val sharedPrefFile = "kotlinsharedpreference"
    var deptSelectedItem: String = ""
    var yearSelectedItem: String = ""
    lateinit var checkBoxClick: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        checkBoxClick = findViewById(R.id.check_box)

        val sharedPreference: SharedPreference = SharedPreference(this)

        /*************************************** Buggy Code ***************************************/

        if (deptSelectedItem != null && yearSelectedItem != null) {
            sharedPreference.getValueString("dept_sp", deptSelectedItem)
            sharedPreference.getValueString("year_sp", yearSelectedItem)
            checkLink()
        }

        /*************************************** Buggy Code ***************************************/
//        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
//            sharedPrefFile,
//            Context.MODE_PRIVATE
//        )
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        sp_department_xml.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                deptSelectedItem = adapterView?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        sp_year_xml.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                yearSelectedItem = adapterView?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        text_submit_xml.setOnClickListener {
            checkLink()
            if (checkBoxClick.isChecked) {
                sharedPreference.save("dept_sp", deptSelectedItem)
                sharedPreference.save("year_sp", yearSelectedItem)
            }
        }
    }

    fun checkLink() {
        when (deptSelectedItem) {
            "", "Select Department" -> Toast.makeText(
                this,
                "Enter your Department",
                Toast.LENGTH_SHORT
            ).show()
            "BCA" -> if (yearSelectedItem == "" || yearSelectedItem == "Select Year") {
                Toast.makeText(this, "Enter your Year", Toast.LENGTH_SHORT).show()
            } else if (yearSelectedItem.equals("1")) {
                linkMakerFun("https://hadi7653.github.io/timetable/time.html?c=BCA&y=1&f=bca")
            } else if (yearSelectedItem.equals("2")) {
                linkMakerFun("https://hadi7653.github.io/timetable/time.html?c=BCA&y=2&f=bca")
            } else if (yearSelectedItem.equals("3")) {
                linkMakerFun("https://hadi7653.github.io/timetable/time.html?c=BCA&y=3&f=bca")
            }
            "BSC CS" -> if (yearSelectedItem == "" || yearSelectedItem == "Select Year") {
                Toast.makeText(this, "Enter your Year", Toast.LENGTH_SHORT).show()
            } else if (yearSelectedItem.equals("1")) {
                linkMakerFun("https://hadi7653.github.io/timetable/time.html?c=B.Sc.Computer-science&y=1&f=cs")
            } else if (yearSelectedItem.equals("2")) {
                linkMakerFun("https://hadi7653.github.io/timetable/time.html?c=B.Sc.Computer-science&y=2&f=cs")
            } else if (yearSelectedItem.equals("3")) {
                linkMakerFun("https://hadi7653.github.io/timetable/time.html?c=B.Sc.Computer-science&y=3&f=cs")
            }

        }

    }

    private fun linkMakerFun(url: String) {
        val j = Intent(applicationContext, webViewActivity::class.java)
        j.putExtra("urltoload", url)
        j.putExtra("syllabusPage", true)
        startActivity(j)
    }

}