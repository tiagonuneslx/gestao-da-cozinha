package com.example.gestaodacozinha.ui.registos.produtos

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.Marca
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.utils.criarFicheiroDeImagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AdicionarProdutosViewModel @ViewModelInject constructor(
    private val database: AppDatabase,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.categoriaDao.obterTodas()
    val marcas = database.marcaDao.obterTodas()

    val nome = MutableLiveData("")
    val categoria = MutableLiveData<Categoria>(null)
    val marca = MutableLiveData<Marca>(null)

    private val _fotoUri = MutableLiveData<Uri>(null)
    val fotoUri: LiveData<Uri>
        get() = _fotoUri
    private var novaFotoUri: Uri? = null

    private val _pedirPermissoes = MutableLiveData(false)
    val pedirPermissoes: LiveData<Boolean>
        get() = _pedirPermissoes

    private val _tirarFotoPedido = MutableLiveData<Intent>(null)
    val tirarFotoPedido: LiveData<Intent>
        get() = _tirarFotoPedido

    private val _navegarProdutos = MutableLiveData(false)
    val navegarProdutos: LiveData<Boolean>
        get() = _navegarProdutos

    val context = getApplication<GestaoCozinhaApp>()

    fun tirarFotoClicado(view: View) {
        _pedirPermissoes.value = true
    }

    fun pedirPermissoesConcluido() {
        _pedirPermissoes.value = false
    }

    fun tirarFoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(context.packageManager)?.also {
                novaFotoUri = FileProvider.getUriForFile(
                    context,
                    "com.example.gestaodacozinha.fileprovider",
                    criarFicheiroDeImagem(context)
                )
                Timber.d("Ficheiro para guardar imagem criado, URI: $novaFotoUri")
                intent.putExtra(MediaStore.EXTRA_OUTPUT, novaFotoUri)
                _tirarFotoPedido.value = intent
            }
                ?: Timber.w("Não há nenhuma app de câmera que reconheça o intent ACTION_IMAGE_CAPTURE!")
        }
    }

    fun tirarFotoPedidoConcluido() {
        _tirarFotoPedido.value = null
    }

    fun fotoTirada() {
        _fotoUri.value?.let {
            val deleteResultCode = context.contentResolver.delete(it, null, null)
            Timber.d("deleteResultCode: $deleteResultCode, URI: $it")
        }
        _fotoUri.value = novaFotoUri
    }

    fun guardarProduto(view: View) {
        if (nome.value == null || nome.value == "" || categoria.value == null || marca.value == null || fotoUri.value == null) {
            Timber.w("Há campos por preencher!")
        } else {
            val produto = Produto(
                nome = nome.value!!,
                categoria = categoria.value!!.nome,
                marca = marca.value!!.nome,
                foto = fotoUri.value!!.toString()
            )
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    database.produtoDao.inserir(produto)
                }
                _navegarProdutos.value = true
            }
        }
    }

    fun navegarProdutosConcluido() {
        _navegarProdutos.value = false
    }
}