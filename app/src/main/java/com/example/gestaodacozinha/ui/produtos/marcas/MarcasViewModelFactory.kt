package com.example.gestaodacozinha.ui.produtos.marcas

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gestaodacozinha.data.ProdutosDao

class MarcasViewModelFactory(
    private val dataSource: ProdutosDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarcasViewModel::class.java)) {
            return MarcasViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
