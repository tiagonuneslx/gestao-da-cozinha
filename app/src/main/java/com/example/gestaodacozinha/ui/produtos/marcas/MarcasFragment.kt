package com.example.gestaodacozinha.ui.produtos.marcas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.databinding.MarcasFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarcasFragment : Fragment() {

    private val viewModel: MarcasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = MarcasFragmentBinding.inflate(inflater, container, false)

        val adapter = MarcaAdapter(MarcaAdapter.OnClickListener { marca ->
            viewModel.marcaClicada(marca)
        })
        binding.listaMarcas.adapter = adapter

        viewModel.marcas.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}