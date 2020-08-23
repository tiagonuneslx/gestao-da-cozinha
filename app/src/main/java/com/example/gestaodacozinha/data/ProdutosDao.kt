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
    fun inserirProduto(produto: Produto): Long

    @Update
    fun atualizarProduto(produto: Produto)

    @Delete
    fun removerProduto(produto: Produto)

    @Query("SELECT * FROM produto")
    fun obterTodosProdutos(): LiveData<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Delete
    fun removerProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirCategoria(categoria: Categoria)

    @Delete
    fun removerCategoria(categoria: Categoria)

    @Query("SELECT * FROM categoria")
    fun obterTodasCategorias(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirMarca(marca: Marca)

    @Delete
    fun removerMarca(marca: Marca)

}