package com.example.gestaodacozinha.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProdutosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirProduto(produto: Produto): Long

    @Update
    suspend fun alterarProduto(produto: Produto)

    @Delete
    suspend fun apagarProduto(produto: Produto)

    @Query("SELECT * FROM produtos ORDER BY id DESC")
    fun obterTodosProdutos(): LiveData<List<Produto>>

    @Transaction
    @Query("SELECT * FROM produtos WHERE id = :produtoId")
    fun obterProdutoComTudo(produtoId: Long): LiveData<ProdutoComTudo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Delete
    suspend fun apagarProdutoQuantidade(produtoQuantidade: ProdutoQuantidade)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirCategoria(categoria: Categoria)

    @Delete
    suspend fun apagarCategoria(categoria: Categoria)

    @Query("SELECT * FROM categorias ORDER BY nome")
    fun obterTodasCategorias(): LiveData<List<Categoria>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirMarca(marca: Marca)

    @Delete
    suspend fun apagarMarca(marca: Marca)

    @Query("SELECT * FROM marcas ORDER BY nome")
    fun obterTodasMarcas(): LiveData<List<Marca>>
}