package com.example.gestaodacozinha.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.MainFragmentBinding
import com.example.gestaodacozinha.ui.categorias.CategoriasViewModel
import com.example.gestaodacozinha.ui.categorias.CategoriasViewModelFactory

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = MainFragmentBinding.inflate(inflater, container, false)

        val viewModel: MainViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            MainViewModelFactory(dataSource, application)
        }

        viewModel.produtos.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isEmpty()) {
                    viewModel.adicionarProdutoExemplo()
                }
            }
        }

        val adapter = ProdutoAdapter()
        binding.listaProdutos.adapter = adapter

        viewModel.produtos.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}