package com.example.gestaodacozinha.ui.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gestaodacozinha.data.Categoria
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.databinding.ListItemCategoriaBinding
import com.example.gestaodacozinha.databinding.ListItemProdutoBinding

class CategoriaAdapter :
    ListAdapter<Categoria, CategoriaAdapter.ViewHolder>(CategoriaDiffCallback()) {

    class ViewHolder private constructor(private val binding: ListItemCategoriaBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCategoriaBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Categoria) {
            binding.categoria = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class CategoriaDiffCallback : DiffUtil.ItemCallback<Categoria>() {
    override fun areItemsTheSame(oldItem: Categoria, newItem: Categoria): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Categoria, newItem: Categoria): Boolean {
        return oldItem.nome == newItem.nome
    }
}