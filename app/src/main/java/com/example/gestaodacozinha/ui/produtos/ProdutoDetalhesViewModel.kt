package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao

class ProdutoDetalhesViewModel(
    val database: ProdutosDao,
    produto: Produto,
    application: Application
) : AndroidViewModel(application) {

    val produtoComTudo = database.obterProdutoComTudo(produto.id)

}