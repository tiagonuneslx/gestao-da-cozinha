package com.example.gestaodacozinha.data

import androidx.room.Entity

@Entity(
    tableName = "produto_quantidade",
    primaryKeys = ["produtoId", "quantidade"]
)
data class ProdutoQuantidade(
    val produtoId: Float,
    val quantidade: Float,
)