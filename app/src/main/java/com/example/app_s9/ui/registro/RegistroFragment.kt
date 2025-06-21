package com.example.app_s9.ui.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_s9.SharedPreferencesHelper
import com.example.app_s9.databinding.FragmentRegistroBinding

class RegistroFragment : Fragment() {
    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegistroViewModel
    private lateinit var sharedPrefs: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefs = SharedPreferencesHelper(requireContext())
        viewModel = ViewModelProvider(this, RegistroViewModelFactory(sharedPrefs))
            .get(RegistroViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            guardarRegistro()
        }

        binding.btnCargar.setOnClickListener {
            cargarRegistro()
        }
    }

    private fun guardarRegistro() {
        val nombre = binding.tilNombre.editText?.text.toString()
        val edad = binding.tilEdad.editText?.text.toString().toIntOrNull() ?: 0
        val email = binding.tilEmail.editText?.text.toString()

        if (validarFormulario(nombre, edad, email)) {
            viewModel.guardarRegistro(nombre, edad, email)
            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        }
    }

    private fun cargarRegistro() {
        val (nombre, edad, email) = viewModel.cargarRegistro()

        if (nombre == null || edad == null || email == null) {
            Toast.makeText(requireContext(), "No hay datos guardados", Toast.LENGTH_SHORT).show()
            binding.tvDatosRegistro.text = ""
            return
        }

        val datos = """
            Nombre: $nombre
            
            Edad: $edad
            
            Email: $email
        """.trimIndent()

        binding.tvDatosRegistro.text = datos
        Toast.makeText(requireContext(), "Datos cargados", Toast.LENGTH_SHORT).show()
    }

    private fun limpiarCampos() {
        binding.tilNombre.editText?.text?.clear()
        binding.tilEdad.editText?.text?.clear()
        binding.tilEmail.editText?.text?.clear()
        binding.tilNombre.error = null
        binding.tilEdad.error = null
        binding.tilEmail.error = null
    }

    private fun validarFormulario(nombre: String, edad: Int, email: String): Boolean {
        var isValid = true

        if (nombre.isBlank()) {
            binding.tilNombre.error = "Ingrese un nombre"
            isValid = false
        } else {
            binding.tilNombre.error = null
        }

        if (edad <= 0) {
            binding.tilEdad.error = "Edad inválida"
            isValid = false
        } else {
            binding.tilEdad.error = null
        }

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email inválido"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Factory para el ViewModel (se mantiene igual)
class RegistroViewModelFactory(private val sharedPrefs: SharedPreferencesHelper) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistroViewModel(sharedPrefs) as T
    }
}