package com.winter.check24codingchalleng.service.utils

import android.content.Context
import android.content.SharedPreferences


object UtilsSharePreferences {

    private const val coding = "codingChallenge"


    fun setCurrentScore(key: String?, value: Int,context:Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(coding, 0)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getCurrentScore(key: String?,context:Context): Int {
        val prefs: SharedPreferences = context.getSharedPreferences(coding, 0)
        return prefs.getInt(key, 0)
    }
}