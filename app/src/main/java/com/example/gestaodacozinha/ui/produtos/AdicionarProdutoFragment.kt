package com.example.gestaodacozinha.ui.produtos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.databinding.AdicionarProdutoFragmentBinding
import com.example.gestaodacozinha.utils.createImageFile
import timber.log.Timber

const val REQUEST_IMAGE_CAPTURE = 1

class AdicionarProdutoFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AdicionarProdutoFragmentBinding.inflate(inflater, container, false)

        val viewModel: AdicionarProdutosViewModel by viewModels {
            val application = requireActivity().application
            val dataSource = AppDatabase.getInstance(application).produtosDao
            AdicionarProdutosViewModelFactory(dataSource, application)
        }

        viewModel.categorias.observe(viewLifecycleOwner) {
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.categoriaSpinner.adapter = adapter
            }
        }

        viewModel.marcas.observe(viewLifecycleOwner) {
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.marcaSpinner.adapter = adapter
            }
        }

        // binding.categoriaSpinner.onItemSelectedListener = this

        binding.tirarFotoBotao.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.gestaodacozinha.fileprovider",
                        createImageFile(requireContext())
                    )
                    Timber.i("Imagem guardada, URI: $photoURI")
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}