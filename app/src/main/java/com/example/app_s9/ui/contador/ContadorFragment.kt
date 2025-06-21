package com.example.app_s9.ui.contador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.app_s9.databinding.FragmentContadorBinding

class ContadorFragment : Fragment() {
    private var _binding: FragmentContadorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ContadorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ContadorViewModel::class.java)
        actualizarContador()

        binding.btnReset.setOnClickListener {
            viewModel.resetearContador()
            actualizarContador()
        }
    }

    private fun actualizarContador() {
        binding.tvContador.text = "Veces abierta la app: ${viewModel.obtenerContador()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}