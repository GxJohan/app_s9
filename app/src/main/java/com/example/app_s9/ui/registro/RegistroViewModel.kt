package com.example.app_s9.ui.registro

import androidx.lifecycle.ViewModel
import com.example.app_s9.SharedPreferencesHelper

class RegistroViewModel(private val sharedPrefs: SharedPreferencesHelper) : ViewModel() {

    fun guardarRegistro(nombre: String, edad: Int, email: String) {
        sharedPrefs.saveString(SharedPreferencesHelper.KEY_NOMBRE, nombre)
        sharedPrefs.saveInt(SharedPreferencesHelper.KEY_EDAD, edad)
        sharedPrefs.saveString(SharedPreferencesHelper.KEY_EMAIL, email)
    }

    fun cargarRegistro(): Triple<String?, Int?, String?> {
        return Triple(
            sharedPrefs.getString(SharedPreferencesHelper.KEY_NOMBRE),
            sharedPrefs.getInt(SharedPreferencesHelper.KEY_EDAD, -1).takeIf { it != -1 },
            sharedPrefs.getString(SharedPreferencesHelper.KEY_EMAIL)
        )
    }
}