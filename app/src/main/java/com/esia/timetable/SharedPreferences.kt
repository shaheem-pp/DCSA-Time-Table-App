package com.esia.timetable

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val PREFS_NAME = ""
    val sharedPrefD: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val PREFS_NAME_2 = ""
    val sharedPrefY: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME_2, Context.MODE_PRIVATE)
    private val PREFS_NAME_3 = ""
    val sharedPrefB:SharedPreferences=context.getSharedPreferences(PREFS_NAME_3,Context.MODE_PRIVATE)

    fun bsave(KEY_NAME: String,value: Boolean){
        val editorB : SharedPreferences.Editor = sharedPrefB.edit()
        editorB.putBoolean(KEY_NAME,value)
        editorB.commit()
    }

    fun dsave(KEY_NAME: String, value: String) {
        val editorD: SharedPreferences.Editor = sharedPrefD.edit()
        editorD.putString(KEY_NAME, value)
        editorD.commit()
    }

    fun ysave(KEY_NAME: String, value: String) {
        val editorY: SharedPreferences.Editor = sharedPrefY.edit()
        editorY.putString(KEY_NAME, value)
        editorY.commit()
    }

    fun getBValueBoolean(KEY_NAME: String, value: Boolean): Boolean?{
        return  sharedPrefB.getBoolean(KEY_NAME,value)
    }

    fun getDValueString(KEY_NAME: String, value: String): String? {

        return sharedPrefD.getString(KEY_NAME, value)
    }

    fun getYValueString(KEY_NAME: String, value: String): String? {

        return sharedPrefY.getString(KEY_NAME, value)
    }
}