package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao

class ProdutoDetalhesViewModelFactory(
    private val dataSource: ProdutosDao,
    private val produto: Produto,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProdutoDetalhesViewModel::class.java)) {
            return ProdutoDetalhesViewModel(dataSource, produto, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
