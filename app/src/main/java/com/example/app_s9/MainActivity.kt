package com.example.app_s9

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.app_s9.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var themeSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar tema antes de setContentView
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        applyTheme()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)

        // Configurar Bottom Navigation
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    private fun applyTheme() {
        val isDarkMode = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_THEME_MODE, false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)

        val switchItem = menu.findItem(R.id.action_theme_switch)
        val switchView = switchItem.actionView

        val themeSwitch = switchView?.findViewById<SwitchCompat>(R.id.theme_switch)
        val themeIcon = switchView?.findViewById<ImageView>(R.id.theme_icon)

        // Estado inicial
        val isDarkMode = sharedPreferencesHelper.getBoolean(
            SharedPreferencesHelper.KEY_THEME_MODE,
            false
        )
        if (themeSwitch != null) {
            themeSwitch.isChecked = isDarkMode
        }
        if (themeIcon != null) {
            updateThemeIcon(themeIcon, isDarkMode)
        }

        // Listener para cambios
        themeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesHelper.saveBoolean(
                SharedPreferencesHelper.KEY_THEME_MODE,
                isChecked
            )
            if (themeIcon != null) {
                updateThemeIcon(themeIcon, isChecked)
            }
            applyTheme()
            recreate()
        }

        return true
    }

    private fun updateThemeIcon(imageView: ImageView, isDarkMode: Boolean) {
        imageView.setImageResource(
            if (isDarkMode) R.drawable.baseline_nightlight_24 else R.drawable.baseline_wb_sunny_24
        )
        // Forzar color blanco siempre
        imageView.setColorFilter(ContextCompat.getColor(this, android.R.color.white))
    }
}