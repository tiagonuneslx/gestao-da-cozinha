package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ProdutosViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val produtos = database.obterTodosProdutos()

    private var apagar = false

    fun produtoClicado(produto: Produto) {
        if (apagar) apagarProduto(produto)
    }

    private fun apagarProduto(produto: Produto) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.apagarProduto(produto)
                Timber.d("Produto apagado: $produto.nome")
            }
        }
    }

    fun alternarApagar(view: View) {
        apagar = !apagar
        alternarCorApagar(
            getApplication<GestaoCozinhaApp>(),
            view as ImageView,
            apagar
        )
    }
}