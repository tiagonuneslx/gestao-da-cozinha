package com.example.gestaodacozinha.ui.produtos

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gestaodacozinha.databinding.AdicionarProdutoFragmentBinding
import com.example.gestaodacozinha.utils.REQUEST_IMAGE_CAPTURE
import com.example.gestaodacozinha.utils.permissoesNecessarias
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdicionarProdutoFragment : Fragment() {

    private lateinit var binding: AdicionarProdutoFragmentBinding

    private val viewModel: AdicionarProdutosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AdicionarProdutoFragmentBinding.inflate(inflater, container, false)

        viewModel.pedirPermissoes.observe(viewLifecycleOwner) {
            if (it != null && it) {
                permissoesNecessarias(
                    requireActivity() as AppCompatActivity,
                    listOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                ) {
                    viewModel.tirarFoto()
                }
                viewModel.pedirPermissoesConcluido()
            }
        }

        viewModel.tirarFotoPedido.observe(viewLifecycleOwner) {
            it?.let { intent ->
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                viewModel.tirarFotoPedidoConcluido()
            }
        }

        viewModel.navegarProdutos.observe(viewLifecycleOwner) {
            if (it != null && it) {
                findNavController().navigate(AdicionarProdutoFragmentDirections.actionAdicionarProdutoFragmentToProdutos())
                viewModel.navegarProdutosConcluido()
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewModel.fotoTirada()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}