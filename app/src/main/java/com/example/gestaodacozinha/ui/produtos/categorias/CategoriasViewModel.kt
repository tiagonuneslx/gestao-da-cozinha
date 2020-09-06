package com.example.gestaodacozinha.ui.produtos.categorias

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.ProdutosDao
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class CategoriasViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.obterTodasCategorias()

    val novaCategoriaNome = MutableLiveData("")

    private var apagar = false

    fun adicionarCategoria(view: View) {
        novaCategoriaNome.value?.let {
            val categoria = Categoria(it)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    database.inserirCategoria(categoria)
                }
            }
        }
    }

    fun categoriaClicada(categoria: Categoria) {
        if (apagar) apagarCategoria(categoria)
    }

    private fun apagarCategoria(categoria: Categoria) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.apagarCategoria(categoria)
                Timber.d("Categoria apagada: $categoria.nome")
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