package com.example.gestaodacozinha.ui.registos.produtos

import android.app.Application
import android.view.MenuItem
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.data.registos.Produto
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdutosViewModel @ViewModelInject constructor(
    private val database: AppDatabase,
    application: Application
) : AndroidViewModel(application) {

    val produtos = database.produtoDao.obterTodos()

    private val _navegarProdutoDetalhes = MutableLiveData<Pair<Produto, View>>(null)
    val navegarProdutoDetalhes: LiveData<Pair<Produto, View>>
        get() = _navegarProdutoDetalhes

    private var apagar = false

    fun produtoClicado(produto: Produto, view: View) {
        if (apagar) apagarProduto(produto)
        else _navegarProdutoDetalhes.value = Pair(produto, view)
    }

    fun navegarProdutoDetalhesConcluido() {
        _navegarProdutoDetalhes.value = null
    }

    private fun apagarProduto(produto: Produto) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.produtoDao.apagar(produto)
            }
        }
    }

    fun alternarApagar(item: MenuItem) {
        apagar = !apagar
        alternarCorApagar(
            getApplication<GestaoCozinhaApp>(),
            item,
            apagar
        )
    }
}