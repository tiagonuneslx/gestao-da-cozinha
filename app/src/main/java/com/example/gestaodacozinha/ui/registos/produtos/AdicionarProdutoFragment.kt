package com.example.gestaodacozinha.ui.registos.produtos

import android.Manifest
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gestaodacozinha.R
import com.example.gestaodacozinha.databinding.AdicionarProdutoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class AdicionarProdutoFragment : Fragment() {

    private lateinit var binding: AdicionarProdutoFragmentBinding

    private val viewModel: AdicionarProdutoViewModel by viewModels()

    private val pedirPermissaoLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { sucesso ->
        if (sucesso) {
            viewModel.tirarFoto()
        } else {
            Timber.w("Não foi concedido o acesso à câmera")
        }
    }

    private val tirarFotoLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { sucesso ->
        if (sucesso) {
            viewModel.fotoTirada()
        } else {
            Timber.w("Não foi possível tirar foto!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AdicionarProdutoFragmentBinding.inflate(inflater, container, false)

        viewModel.pedirPermissoes.observe(viewLifecycleOwner) {
            if (it != null && it) {
                pedirPermissaoLauncher.launch(Manifest.permission.CAMERA)
                viewModel.pedirPermissoesConcluido()
            }
        }

        viewModel.tirarFotoPedido.observe(viewLifecycleOwner) {
            it?.let { uri ->
                tirarFotoLauncher.launch(uri)
                viewModel.tirarFotoPedidoConcluido()
            }
        }

        viewModel.navegarProdutos.observe(viewLifecycleOwner) {
            if (it != null && it) {
                findNavController().navigate(AdicionarProdutoFragmentDirections.actionAdicionarProdutoFragmentToProdutos())
                viewModel.navegarProdutosConcluido()
            }
        }

        val adicionarCategoriaDialog: AlertDialog = buildAdicionarCategoriaDialog()

        binding.botaoAdicionarCategoria.setOnClickListener {
            adicionarCategoriaDialog.show()
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun buildAdicionarCategoriaDialog(): AlertDialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            val input = EditText(requireContext())
            input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            setView(input)
            setPositiveButton(
                R.string.ok
            ) { dialog, id ->
                Timber.d(input.toString())
            }
            setNegativeButton(
                R.string.cancel
            ) { dialog, id -> }
            setTitle("Adicionar categoria")
        }
        return builder.create()
    }
}