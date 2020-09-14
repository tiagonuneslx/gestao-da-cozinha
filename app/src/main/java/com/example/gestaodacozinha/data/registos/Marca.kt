package com.example.gestaodacozinha.data.registos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marcas")
data class Marca(
    @PrimaryKey
    val nome: String,
    val branca: Boolean = true
) {
    override fun toString() = "$nome${if (branca) " (Branca)" else ""}"

}