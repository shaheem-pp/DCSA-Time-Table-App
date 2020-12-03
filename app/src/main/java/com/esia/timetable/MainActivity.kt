package com.esia.timetable

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_main.*

//import java.text.SimpleDateFormat
//import java.util.*

class MainActivity : AppCompatActivity() {
    private var mNotified = false
    var deptSelectedItem: String = ""
    var yearSelectedItem: String = ""
    lateinit var checkBoxClick: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
//        val simpleDateFormat = SimpleDateFormat("HH:mm:ss z")
//        val currentDateAndTime: String = simpleDateFormat.format(Date())
//        if (currentDateAndTime == "09:15:00") {
//            Alerter.create(this)
//                .setTitle("Timetable")
//                .setText("Your class is about to start")
//                .setIcon(R.drawable.applogo)
//                .show()
//        }


        checkBoxClick = findViewById(R.id.check_box)
        var sp_department_xml = findViewById<Spinner>(R.id.sp_department_xml)
        var sp_year_xml = findViewById<Spinner>(R.id.sp_year_xml)


        val sharedPreference: SharedPreference = SharedPreference(this)



        if (deptSelectedItem != null && yearSelectedItem != null) {
            deptSelectedItem =
                sharedPreference.getDValueString("dept_sp", deptSelectedItem).toString()
            yearSelectedItem =
                sharedPreference.getYValueString("year_sp", yearSelectedItem).toString()
            checkLink()
        }


//
//
//


//        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
//            sharedPrefFile,
//            Context.MODE_PRIVATE
//        )
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        val dArrayAdapter : ArrayAdapter<Any?> = ArrayAdapter<Any?>(this,R.layout.color_spinner_layout,departmentArray)
//        val yArrayAdapter : ArrayAdapter<Any?> = ArrayAdapter<Any?>(this,R.layout.color_spinner_layout,yearArray)


        val departmentArray = resources.getStringArray(R.array.departments)
        val yearArray = resources.getStringArray(R.array.year)

        if (sp_department_xml != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.spinnerlayout, departmentArray
            )
            adapter.setDropDownViewResource(R.layout.dropdownlayout)
            sp_department_xml.adapter = adapter
        }

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

        if (sp_year_xml != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.spinnerlayout, yearArray
            )
            adapter.setDropDownViewResource(R.layout.dropdownlayout)
            sp_year_xml.adapter = adapter
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
                sharedPreference.dsave("dept_sp", deptSelectedItem)
                sharedPreference.ysave("year_sp", yearSelectedItem)
                val k = Intent(applicationContext, webViewActivity::class.java)
                k.putExtra("state", true)
                startActivity(k)
            }
        }
        text_web_xml.setOnClickListener {
//            val i = Intent(applicationContext, webViewActivity::class.java)
//            i.putExtra("urltoload", "https://aquibe.github.io/e-timetable/")
//            startActivity(i)
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://sias.edu.in/timetable/"))
            startActivity(i)
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
                linkMakerFun("https://sias.edu.in/timetable/tt.html?classname=bca&year=1")
            } else if (yearSelectedItem.equals("2")) {
                linkMakerFun("https://sias.edu.in/timetable/tt.html?classname=bca&year=2")
            } else if (yearSelectedItem.equals("3")) {
                linkMakerFun("https://sias.edu.in/timetable/tt.html?classname=bca&year=3")
            }
            "BSC CS" -> if (yearSelectedItem == "" || yearSelectedItem == "Select Year") {
                Toast.makeText(this, "Enter your Year", Toast.LENGTH_SHORT).show()
            } else if (yearSelectedItem.equals("1")) {
                linkMakerFun("https://sias.edu.in/timetable/tt.html?classname=bcs&year=1")
            } else if (yearSelectedItem.equals("2")) {
                linkMakerFun("https://sias.edu.in/timetable/tt.html?classname=bcs&year=2")
            } else if (yearSelectedItem.equals("3")) {
                linkMakerFun("https://sias.edu.in/timetable/tt.html?classname=bcs&year=3")
            }

        }

    }

    private fun linkMakerFun(url: String) {
        val j = Intent(applicationContext, webViewActivity::class.java)
        j.putExtra("urltoload", url)
        startActivity(j)
    }

//    fun notify() {
//
//    }

}