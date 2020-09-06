package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao

class ProdutoDetalhesViewModel(
    val database: ProdutosDao,
    produto: Produto,
    application: Application
) : AndroidViewModel(application) {

    private val _produto = MutableLiveData<Produto>()
    val produto: LiveData<Produto>
        get() = _produto

    init {
        _produto.value = produto
    }

}