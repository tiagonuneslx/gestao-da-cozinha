package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.Marca
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.data.ProdutosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AdicionarProdutosViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.obterTodasCategorias()
    val marcas = database.obterTodasMarcas()

}