package com.example.gestaodacozinha.ui.registos.produtos

import android.app.Application
import android.net.Uri
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
import com.example.gestaodacozinha.utils.obterUriParaFoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AdicionarProdutoViewModel @ViewModelInject constructor(
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

    private val _tirarFotoPedido = MutableLiveData<Uri>(null)
    val tirarFotoPedido: LiveData<Uri>
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
        novaFotoUri = FileProvider.getUriForFile(
            context,
            "com.example.gestaodacozinha.fileprovider",
            obterUriParaFoto(context)
        )
        _tirarFotoPedido.value = novaFotoUri
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