package com.example.app_s9

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    
    companion object {
        private const val PREF_NAME = "AppS9Preferences"
        
        // Claves para las preferencias
        const val KEY_USERNAME = "username"
        const val KEY_IS_FIRST_TIME = "is_first_time"
        const val KEY_USER_ID = "user_id"

        // Tema
        const val KEY_THEME_MODE = "theme_mode"

        // Contador
        const val KEY_APP_LAUNCHES = "app_launch_count"

        // Registro
        const val KEY_NOMBRE = "registro_nombre"
        const val KEY_EDAD = "registro_edad"
        const val KEY_EMAIL = "registro_email"
    }
    
    // Métodos para String
    fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }
    
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
    
    // Métodos para Boolean
    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }
    
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
    
    // Métodos para Int
    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }
    
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }
    
    // Métodos para Float
    fun saveFloat(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }
    
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }
    
    // Métodos para Long
    fun saveLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }
    
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }
    
    // Método para eliminar una preferencia
    fun remove(key: String) {
        editor.remove(key).apply()
    }
    
    // Método para limpiar todas las preferencias
    fun clearAll() {
        editor.clear().apply()
    }
    
    // Método para verificar si existe una clave
    fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }
}