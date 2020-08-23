package com.example.gestaodacozinha.ui.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.CategoriasFragmentBinding

class CategoriasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = CategoriasFragmentBinding.inflate(inflater, container, false)

        val viewModel: CategoriasViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            CategoriasViewModelFactory(dataSource, application)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}