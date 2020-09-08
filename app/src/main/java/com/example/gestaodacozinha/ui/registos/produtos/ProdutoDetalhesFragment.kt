package com.example.gestaodacozinha.ui.registos.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.databinding.ProdutoDetalhesFragmentBinding
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdutoDetalhesFragment : Fragment() {

    private val viewModel: ProdutoDetalhesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ProdutoDetalhesFragmentBinding.inflate(inflater, container, false)

        sharedElementEnterTransition = MaterialContainerTransform()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}