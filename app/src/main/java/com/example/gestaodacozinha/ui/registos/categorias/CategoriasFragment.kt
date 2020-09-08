package com.example.gestaodacozinha.ui.registos.categorias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.databinding.CategoriasFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriasFragment : Fragment() {

    private val viewModel: CategoriasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = CategoriasFragmentBinding.inflate(inflater, container, false)

        val adapter = CategoriaAdapter(CategoriaAdapter.OnClickListener { categoria ->
            viewModel.categoriaClicada(categoria)
        })
        binding.listaCategorias.adapter = adapter

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}