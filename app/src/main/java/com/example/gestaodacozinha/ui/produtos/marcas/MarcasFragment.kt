package com.example.gestaodacozinha.ui.produtos.marcas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.MarcasFragmentBinding

class MarcasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = MarcasFragmentBinding.inflate(inflater, container, false)

        val viewModel: MarcasViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            MarcasViewModelFactory(dataSource, application)
        }

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