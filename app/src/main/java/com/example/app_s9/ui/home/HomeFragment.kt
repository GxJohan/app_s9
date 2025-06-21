package com.example.app_s9.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app_s9.SharedPreferencesHelper
import com.example.app_s9.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        setupListeners()
        checkFirstTime()
    }

    private fun setupListeners() {
        binding.buttonSave.setOnClickListener { saveData() }
        binding.buttonLoad.setOnClickListener { loadData() }
        binding.buttonClear.setOnClickListener { clearAllData() }
    }

    private fun saveData() {
        val username = binding.editTextUsername.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }

        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, username)
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())

        Toast.makeText(requireContext(), "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        binding.editTextUsername.setText("")
    }

    private fun loadData() {
        val username = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME, "Sin nombre")
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)

        val result = "Usuario: $username\n\nID: $userId\n\nPrimera vez: ${if (isFirstTime) "Sí" else "No"}"
        binding.textViewResult.text = result
    }

    private fun clearAllData() {
        sharedPreferencesHelper.clearAll()
        binding.textViewResult.text = ""
        binding.editTextUsername.setText("")
        Toast.makeText(requireContext(), "Todas las preferencias han sido eliminadas", Toast.LENGTH_SHORT).show()
    }

    private fun checkFirstTime() {
        val isFirstTime = sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        if (isFirstTime) {
            Toast.makeText(requireContext(), "¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}