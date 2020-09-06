package com.example.gestaodacozinha.ui.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.R
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.ProdutoDetalhesFragmentBinding
import com.google.android.material.transition.MaterialContainerTransform

class ProdutoDetalhesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ProdutoDetalhesFragmentBinding.inflate(inflater, container, false)

        val viewModel: ProdutoDetalhesViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            val produto = ProdutoDetalhesFragmentArgs.fromBundle(requireArguments()).produto
            ProdutoDetalhesViewModelFactory(dataSource, produto, application)
        }

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 400L
            //scrimColor = Color.TRANSPARENT
            //setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}