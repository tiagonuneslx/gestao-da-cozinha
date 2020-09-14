package com.example.gestaodacozinha.utils

import android.content.Context
import android.content.res.ColorStateList
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.gestaodacozinha.R

fun alternarCorApagar(context: Context, imgView: ImageView, apagar: Boolean) {
    if (apagar) {
        val colorInt: Int = ContextCompat.getColor(context, R.color.apagarAtivado)
        imgView.imageTintList = ColorStateList.valueOf(colorInt)
        Toast.makeText(context, "Toque no item para apagar", Toast.LENGTH_SHORT).show()
    } else
        imgView.imageTintList = null
}

fun alternarCorApagar(context: Context, item: MenuItem, apagar: Boolean) {
    if (apagar) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val colorInt: Int = ContextCompat.getColor(context, R.color.apagarAtivadoMenu)
            item.iconTintList = ColorStateList.valueOf(colorInt)
        }
        Toast.makeText(context, "Toque no item para apagar", Toast.LENGTH_SHORT).show()
    } else {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            item.iconTintList = null
        }
    }
}