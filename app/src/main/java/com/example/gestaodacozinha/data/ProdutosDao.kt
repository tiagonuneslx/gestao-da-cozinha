package com.example.gestaodacozinha.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProdutosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirProduto(produto: Produto): Long

    @Update
    suspend fun atualizarProduto(produto: Produto)

    @Delete
    suspend fun removerProduto(produto: Produto)

    @Query("SELECT * FROM produto")
    fun obterTodosProdutos(): LiveData<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Delete
    suspend fun removerProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirCategoria(categoria: Categoria)

    @Delete
    suspend fun removerCategoria(categoria: Categoria)

    @Query("SELECT * FROM categoria")
    fun obterTodasCategorias(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirMarca(marca: Marca)

    @Delete
    suspend fun removerMarca(marca: Marca)

    @Query("SELECT * FROM marca")
    fun obterTodasMarcas(): LiveData<List<Categoria>>
}