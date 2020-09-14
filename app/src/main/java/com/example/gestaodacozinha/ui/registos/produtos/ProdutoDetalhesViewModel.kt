package com.example.gestaodacozinha.ui.registos.produtos

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.data.registos.Produto
import com.example.gestaodacozinha.data.registos.ProdutoQuantidade
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdutoDetalhesViewModel @ViewModelInject constructor(
    private val database: AppDatabase,
    @Assisted savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {

    private val produtoId = savedStateHandle.get<Produto>("produto")!!.id

    val produtoComTudo = database.produtoDao.obterComTudo(produtoId)

    val produtoQuantidades = database.produtoQuantidadeDao.obterTodas(produtoId)

    val novaQuantidade = MutableLiveData<String>()

    private var apagar = false

    fun adicionarQuantidade(view: View) {
        val novaQuantidadeFloat = novaQuantidade.value?.toFloatOrNull()
        if (novaQuantidadeFloat != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val produtoQuantidade = ProdutoQuantidade(
                        produtoId = produtoComTudo.value!!.produto.id,
                        quantidade = novaQuantidadeFloat
                    )
                    database.produtoQuantidadeDao.inserir(produtoQuantidade)
                }
            }
        }
    }

    fun produtoClicado(produtoQuantidade: ProdutoQuantidade, view: View) {
        if (apagar) apagarProdutoQuantidade(produtoQuantidade)
    }

    private fun apagarProdutoQuantidade(produtoQuantidade: ProdutoQuantidade) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.produtoQuantidadeDao.apagar(produtoQuantidade)
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