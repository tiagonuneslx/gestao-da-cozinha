package com.example.gestaodacozinha.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("content")
fun <E> setContent(recyclerView: RecyclerView, content: List<E>?) {
    content?.let {
        try {
            @Suppress("UNCHECKED_CAST")
            val adapter = recyclerView.adapter as ListAdapter<E, *>
            adapter.submitList(it)
        } catch (e: TypeCastException) {
            throw TypeCastException("O conteúdo não é válido com o RecyclerView")
        }
    }
}