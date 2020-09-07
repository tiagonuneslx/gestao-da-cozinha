package com.example.gestaodacozinha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marca")
data class Marca(
    @PrimaryKey
    val nome: String,
    val branca: Boolean = true
) {
    override fun toString() = "$nome${if (branca) " (Branca)" else ""}"

}