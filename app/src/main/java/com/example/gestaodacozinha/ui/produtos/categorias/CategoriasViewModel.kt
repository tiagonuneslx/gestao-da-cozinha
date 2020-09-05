package com.example.gestaodacozinha.ui.produtos.categorias

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.ProdutosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriasViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.obterTodasCategorias()

    val novaCategoriaNome = MutableLiveData("")

    fun adicionarCategoria() {
        novaCategoriaNome.value?.let {
            val categoria = Categoria(it)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    database.inserirCategoria(categoria)
                }
            }
        }
    }
}