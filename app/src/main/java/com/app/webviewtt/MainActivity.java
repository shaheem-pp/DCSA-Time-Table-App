package com.app.webviewtt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] course = {"Select Course", "BCA", "BCS"};
    String[] semester = {"Select Year", "1", "2", "3"};
    String getCourse, getSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, course);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setAdapter(aa);

        ArrayAdapter aa3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, semester);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spin3 = (Spinner) findViewById(R.id.spinner3);
        spin3.setAdapter(aa3);

        Button submitBtn = (Button) findViewById(R.id.syllSubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCourse = spin.getSelectedItem().toString();
                getSemester = spin3.getSelectedItem().toString();
                checkLink();
            }
        });

    }

    public void checkLink() {
        switch (getCourse) {
            case "Select Course":
                if (getSemester.equals("Select Semester")) {
                    Toast.makeText(this, "Please select a Course and Year", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Please select a Course", Toast.LENGTH_SHORT).show();
                }
                break;

            case "BCA":
                if (getSemester.equals("Select Year")) {
                    Toast.makeText(this, "Please select a Year", Toast.LENGTH_SHORT).show();
                } else if (getSemester.equals("1")) {
                    linkMake("https://hadi7653.github.io/timetable/time.html?c=BCA&y=1&f=bca");
                } else if (getSemester.equals("2")) {
                    linkMake("https://hadi7653.github.io/timetable/time.html?c=BCA&y=2&f=bca");
                } else if (getSemester.equals("3")) {
                    linkMake("https://hadi7653.github.io/timetable/time.html?c=BCA&y=3&f=bca");
                }
                break;


            case "BCS":
                if (getSemester.equals("Select Semester")) {
                    Toast.makeText(this, "Please select a semester", Toast.LENGTH_SHORT).show();
                } else if (getSemester.equals("1")) {
                    linkMake("https://hadi7653.github.io/timetable/time.html?c=B.Sc.Computer-science&y=1&f=cs");
                } else if (getSemester.equals("2")) {
                    linkMake("https://hadi7653.github.io/timetable/time.html?c=B.Sc.Computer-science&y=2&f=cs");
                } else if (getSemester.equals("3")) {
                    linkMake("https://hadi7653.github.io/timetable/time.html?c=B.Sc.Computer-science&y=2&f=cs");
                }
                break;

        }

    }

    public void linkMake(String url) {
        Intent j = new Intent(getApplicationContext(), WebViewOne.class);
        j.putExtra("urltoload", url);
        j.putExtra("syllabusPage", true);
        startActivity(j);
    }
}