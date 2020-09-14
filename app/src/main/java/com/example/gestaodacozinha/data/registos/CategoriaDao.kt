package com.example.gestaodacozinha.data.registos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoriaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(categoria: Categoria)

    @Delete
    suspend fun apagar(categoria: Categoria)

    @Query("SELECT * FROM categorias ORDER BY nome")
    fun obterTodas(): LiveData<List<Categoria>>
}