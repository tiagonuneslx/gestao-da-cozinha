package com.example.gestaodacozinha.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ProdutoQuantidadeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(produtoQuantidade: ProdutoQuantidade)

    @Delete
    suspend fun apagar(produtoQuantidade: ProdutoQuantidade)
}