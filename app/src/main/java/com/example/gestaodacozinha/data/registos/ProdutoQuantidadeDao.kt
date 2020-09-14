package com.example.gestaodacozinha.data.registos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProdutoQuantidadeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(produtoQuantidade: ProdutoQuantidade)

    @Delete
    suspend fun apagar(produtoQuantidade: ProdutoQuantidade)

    @Query("SELECT * FROM produtos_quantidades WHERE produto_id = :produtoId ORDER BY quantidade")
    fun obterTodas(produtoId: Long): LiveData<List<ProdutoQuantidade>>
}