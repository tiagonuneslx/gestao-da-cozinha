package com.example.gestaodacozinha.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "produto")
data class Produto(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val nome: String,
    val foto: String,
    val categoria: String,
    val marca: String,
) : Parcelable