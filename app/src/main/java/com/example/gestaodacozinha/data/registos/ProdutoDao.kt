package com.example.gestaodacozinha.data.registos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProdutoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(produto: Produto): Long

    @Update
    suspend fun alterar(produto: Produto)

    @Delete
    suspend fun apagar(produto: Produto)

    @Query("SELECT * FROM registos ORDER BY id DESC")
    fun obterTodos(): LiveData<List<Produto>>

    @Transaction
    @Query("SELECT * FROM registos WHERE id = :produtoId")
    fun obterComTudo(produtoId: Long): LiveData<ProdutoComTudo>
}