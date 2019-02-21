package com.vodrex.login_sample.utils

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context){
    companion object {
        val DEVELOP_MODE = false
        private const val USER_TOKEN = "data.source.prefs.USER_TOKEN"
        private const val USER_ID = "data.source.prefs.USER_ID"
    }
    private val preferences = PreferenceManager   .getDefaultSharedPreferences(context)
    var userToken = preferences.getString(USER_TOKEN, "")
        set(value) = preferences.edit().putString(USER_TOKEN,     value).apply()

    var userId = preferences.getString(USER_ID, "")
        set(value) = preferences.edit().putString(USER_ID,     value).apply()
}