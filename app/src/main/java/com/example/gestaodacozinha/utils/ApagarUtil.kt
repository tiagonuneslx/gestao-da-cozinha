package com.example.gestaodacozinha.utils

import android.content.Context
import android.content.res.ColorStateList
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