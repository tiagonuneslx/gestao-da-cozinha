package com.example.gestaodacozinha.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProdutosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirProduto(produto: Produto): Long

    @Update
    suspend fun atualizarProduto(produto: Produto)

    @Delete
    suspend fun removerProduto(produto: Produto)

    @Query("SELECT * FROM produto ORDER BY id DESC")
    fun obterTodosProdutos(): LiveData<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Delete
    suspend fun removerProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirCategoria(categoria: Categoria)

    @Delete
    suspend fun removerCategoria(categoria: Categoria)

    @Query("SELECT * FROM categoria ORDER BY nome")
    fun obterTodasCategorias(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirMarca(marca: Marca)

    @Delete
    suspend fun removerMarca(marca: Marca)

    @Query("SELECT * FROM marca ORDER BY nome")
    fun obterTodasMarcas(): LiveData<List<Marca>>
}