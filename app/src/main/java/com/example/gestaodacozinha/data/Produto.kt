package com.example.gestaodacozinha.data

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "produtos",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["nome"],
            childColumns = ["categoria"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),
        ForeignKey(
            entity = Marca::class,
            parentColumns = ["nome"],
            childColumns = ["marca"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),
    ]
)
data class Produto(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val nome: String,
    val foto: String,
    val categoria: String,
    val marca: String,
) : Parcelable

data class ProdutoComTudo(
    @Embedded val produto: Produto,
    @Relation(
        parentColumn = "categoria",
        entityColumn = "nome",
    )
    val categoria: Categoria,
    @Relation(
        parentColumn = "marca",
        entityColumn = "nome",
    )
    val marca: Marca,
)