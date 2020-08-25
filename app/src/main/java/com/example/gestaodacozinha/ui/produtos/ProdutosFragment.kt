package com.example.gestaodacozinha.ui.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.ProdutosFragmentBinding

class ProdutosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ProdutosFragmentBinding.inflate(inflater, container, false)

        val viewModel: ProdutosViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            ProdutosViewModelFactory(dataSource, application)
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

        binding.botaoAdicionarProduto.setOnClickListener {
            findNavController().navigate(ProdutosFragmentDirections.actionProdutosToAdicionarProdutoFragment())
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}