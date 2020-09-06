package com.example.gestaodacozinha.ui.produtos

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.AdicionarProdutoFragmentBinding
import com.example.gestaodacozinha.utils.REQUEST_IMAGE_CAPTURE
import com.example.gestaodacozinha.utils.apagarFotoAntigaSeExistir
import com.example.gestaodacozinha.utils.preencher
import com.example.gestaodacozinha.utils.tirarFoto
import timber.log.Timber


class AdicionarProdutoFragment : Fragment() {

    lateinit var binding: AdicionarProdutoFragmentBinding

    private val viewModel: AdicionarProdutosViewModel by viewModels {
        val application = requireActivity().application
        val dataSource = AppDatabase.getInstance(application).produtosDao
        AdicionarProdutosViewModelFactory(dataSource, application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AdicionarProdutoFragmentBinding.inflate(inflater, container, false)

        viewModel.categorias.observe(viewLifecycleOwner) {
            it?.let {
                binding.categoriaSpinner.preencher(requireContext(), it)
            }
        }

        viewModel.marcas.observe(viewLifecycleOwner) {
            it?.let {
                binding.marcaSpinner.preencher(requireContext(), it)
            }
        }

        binding.tirarFotoBotao.setOnClickListener {
            viewModel.novaFotoUri = tirarFoto(requireContext(), this)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            apagarFotoAntigaSeExistir(requireContext(), viewModel.fotoUri)
            viewModel.fotoUri = viewModel.novaFotoUri
            Glide.with(this)
                .load(viewModel.fotoUri)
                .into(binding.foto);
            Timber.i("Imagem carregada!")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}