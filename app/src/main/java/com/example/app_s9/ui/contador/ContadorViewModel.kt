package com.example.app_s9.ui.contador

import androidx.lifecycle.ViewModel
import com.example.app_s9.App
import com.example.app_s9.SharedPreferencesHelper

class ContadorViewModel : ViewModel() {
    fun obtenerContador(): Int {
        return App.sharedPrefs.getInt(SharedPreferencesHelper.KEY_APP_LAUNCHES, 0)
    }

    fun resetearContador() {
        App.sharedPrefs.saveInt(SharedPreferencesHelper.KEY_APP_LAUNCHES, 0)
    }
}