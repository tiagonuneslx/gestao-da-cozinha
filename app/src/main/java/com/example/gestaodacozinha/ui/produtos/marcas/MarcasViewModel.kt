package com.example.gestaodacozinha.ui.produtos.marcas

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.AppDatabase
import com.example.gestaodacozinha.data.Marca
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MarcasViewModel @ViewModelInject constructor(
    private val database: AppDatabase,
    application: Application
) : AndroidViewModel(application) {

    val marcas = database.marcaDao.obterTodas()

    val novaMarcaNome = MutableLiveData("")
    val novaMarcaBranca = MutableLiveData(false)

    private var apagar = false

    fun adicionarMarca(view: View) {
        if (novaMarcaNome.value != null && novaMarcaBranca.value != null) {
            val marca = Marca(
                nome = novaMarcaNome.value!!,
                branca = novaMarcaBranca.value!!,
            )
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    database.marcaDao.inserir(marca)
                }
            }
        }
    }

    fun marcaClicada(marca: Marca) {
        if (apagar) apagarMarca(marca)
    }

    private fun apagarMarca(marca: Marca) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.marcaDao.apagar(marca)
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