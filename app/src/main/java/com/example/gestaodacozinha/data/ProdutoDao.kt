package com.example.gestaodacozinha.data

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

    @Query("SELECT * FROM produtos ORDER BY id DESC")
    fun obterTodos(): LiveData<List<Produto>>

    @Transaction
    @Query("SELECT * FROM produtos WHERE id = :produtoId")
    fun obterComTudo(produtoId: Long): LiveData<ProdutoComTudo>
}