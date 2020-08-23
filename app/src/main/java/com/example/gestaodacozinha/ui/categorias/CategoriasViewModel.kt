package com.example.gestaodacozinha.ui.categorias

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gestaodacozinha.data.ProdutosDao

class CategoriasViewModel(
    val database: ProdutosDao,
    application: Application
) : AndroidViewModel(application) {

    val categorias = database.obterTodasCategorias()
}