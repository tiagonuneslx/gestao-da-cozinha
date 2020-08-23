package com.example.gestaodacozinha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produto")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val nome: String,
    val foto: String,
    val categoria: String,
    val marca: String,
)