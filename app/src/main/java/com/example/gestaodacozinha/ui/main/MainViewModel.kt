package com.example.gestaodacozinha.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.Marca
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val produtos = database.obterTodosProdutos()

    fun adicionarProdutoExemplo() {
        Timber.i("Adicionando Produtos")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val categoria = Categoria("Laticínios")
                database.inserirCategoria(categoria)

                val marca = Marca("Mimosa")
                database.inserirMarca(marca)

                val produto = Produto(
                    nome = "Leite Mimosa",
                    foto = "foto_uri",
                    categoria = categoria.nome,
                    marca = marca.nome
                )
                val produtoId = database.inserirProduto(produto)

                Timber.i("Produto exemplo adicionado, ID: $produtoId")
            }
        }
    }
}