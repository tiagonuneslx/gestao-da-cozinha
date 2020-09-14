package com.example.gestaodacozinha.ui.registos.categorias

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.data.registos.Categoria
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CategoriasViewModel @ViewModelInject constructor(
    private val database: AppDatabase,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.categoriaDao.obterTodas()

    val novaCategoriaNome = MutableLiveData("")

    private var apagar = false

    fun adicionarCategoria(view: View) {
        novaCategoriaNome.value?.let {
            val categoria = Categoria(it)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    database.categoriaDao.inserir(categoria)
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
                database.categoriaDao.apagar(categoria)
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