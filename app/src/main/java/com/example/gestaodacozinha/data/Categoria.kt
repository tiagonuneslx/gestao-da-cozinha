package com.example.gestaodacozinha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria")
data class Categoria(
    @PrimaryKey
    val nome: String,
)