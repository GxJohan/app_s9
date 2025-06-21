package com.example.app_s9

import android.app.Application

class App : Application() {
    companion object {
        lateinit var sharedPrefs: SharedPreferencesHelper
    }

    override fun onCreate() {
        super.onCreate()
        sharedPrefs = SharedPreferencesHelper(this)
        incrementLaunchCount()
    }

    private fun incrementLaunchCount() {
        val currentCount = sharedPrefs.getInt(SharedPreferencesHelper.KEY_APP_LAUNCHES, 0)
        sharedPrefs.saveInt(SharedPreferencesHelper.KEY_APP_LAUNCHES, currentCount + 1)
    }
}