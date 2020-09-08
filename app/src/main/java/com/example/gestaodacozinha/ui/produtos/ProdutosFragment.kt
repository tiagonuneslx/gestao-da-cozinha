package com.example.gestaodacozinha.ui.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.gestaodacozinha.R
import com.example.gestaodacozinha.databinding.ProdutosFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdutosFragment : Fragment() {

    private val viewModel: ProdutosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ProdutosFragmentBinding.inflate(inflater, container, false)

        val adapter = ProdutoAdapter(ProdutoAdapter.OnClickListener { produto, view ->
            viewModel.produtoClicado(produto, view)
        })
        binding.listaProdutos.adapter = adapter

        viewModel.navegarProdutoDetalhes.observe(viewLifecycleOwner) {
            it?.let {
                val nomeTransicaoProdutoDetalhes =
                    getString(R.string.nome_transicao_produto_detalhes)
                findNavController().navigate(
                    ProdutosFragmentDirections.actionProdutosToProdutoDetalhesFragment(it.first),
                    FragmentNavigatorExtras(it.second to nomeTransicaoProdutoDetalhes)
                )
                viewModel.navegarProdutoDetalhesConcluido()
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