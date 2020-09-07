package com.example.gestaodacozinha.ui.produtos.marcas

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.GestaoCozinhaApp
import com.example.gestaodacozinha.data.Marca
import com.example.gestaodacozinha.data.ProdutosDao
import com.example.gestaodacozinha.utils.alternarCorApagar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class MarcasViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val marcas = database.obterTodasMarcas()

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
                    database.inserirMarca(marca)
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
                database.apagarMarca(marca)
                Timber.d("Marca apagada: $marca.nome")
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