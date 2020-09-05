package com.example.gestaodacozinha.ui.produtos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gestaodacozinha.data.ProdutosDao

class AdicionarProdutosViewModelFactory(
    private val dataSource: ProdutosDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdicionarProdutosViewModel::class.java)) {
            return AdicionarProdutosViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
