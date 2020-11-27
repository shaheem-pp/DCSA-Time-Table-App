package com.esia.timetable

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, value)

        editor.commit()
    }
    fun getValueString(KEY_NAME: String, deptSelectedItem: String): Int {

        return sharedPref.getInt(KEY_NAME, 0)
    }
}