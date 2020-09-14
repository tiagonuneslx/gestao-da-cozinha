package com.example.gestaodacozinha.data.registos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey
    val nome: String,
) {
    override fun toString() = nome
}