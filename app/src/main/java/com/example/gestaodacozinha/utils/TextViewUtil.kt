package com.example.gestaodacozinha.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("quantidade")
fun setQuantidade(textView: TextView, quantidade: Float) {
    textView.text = quantidade.toString()
}