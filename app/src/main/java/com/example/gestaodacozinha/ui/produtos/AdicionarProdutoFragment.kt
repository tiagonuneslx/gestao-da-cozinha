package com.example.gestaodacozinha.ui.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.AdicionarProdutoFragmentBinding
import com.example.gestaodacozinha.databinding.ProdutosFragmentBinding

class AdicionarProdutoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AdicionarProdutoFragmentBinding.inflate(inflater, container, false)

        val viewModel: ProdutosViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            ProdutosViewModelFactory(dataSource, application)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}