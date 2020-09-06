package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.Marca
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AdicionarProdutosViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.obterTodasCategorias()
    val marcas = database.obterTodasMarcas()

    val nome = MutableLiveData("")
    val categoria = MutableLiveData<Categoria>(null)
    val marca = MutableLiveData<Marca>(null)
    var fotoUri: Uri? = null
    var novaFotoUri: Uri? = null

    fun guardarProduto() {
        if (nome.value == null || nome.value == "" || categoria.value == null || marca.value == null || fotoUri == null) {
            Timber.w("Há campos por preencher!")
        } else {
            val produto = Produto(
                nome = nome.value!!,
                categoria = categoria.value!!.nome,
                marca = marca.value!!.nome,
                foto = fotoUri!!.toString()
            )
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    database.inserirProduto(produto)
                    Timber.d("Produto $produto adicionado com sucesso!")
                }
            }
        }
    }
}