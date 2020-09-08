package com.example.gestaodacozinha.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MarcaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(marca: Marca)

    @Delete
    suspend fun apagar(marca: Marca)

    @Query("SELECT * FROM marcas ORDER BY nome")
    fun obterTodas(): LiveData<List<Marca>>
}