package com.example.gestaodacozinha.ui.registos.produtos

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.data.Produto

class ProdutoDetalhesViewModel @ViewModelInject constructor(
    private val database: AppDatabase,
    @Assisted savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {

    val produtoComTudo = database.produtoDao.obterComTudo(
        savedStateHandle.get<Produto>("produto")!!.id
    )

}