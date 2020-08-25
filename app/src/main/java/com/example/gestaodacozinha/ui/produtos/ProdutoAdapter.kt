package com.example.gestaodacozinha.ui.produtos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.databinding.ListItemProdutoBinding

class ProdutoAdapter :
    ListAdapter<Produto, ProdutoAdapter.ViewHolder>(ProdutoDiffCallback()) {

    class ViewHolder private constructor(private val binding: ListItemProdutoBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemProdutoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Produto) {
            binding.produto = item
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

class ProdutoDiffCallback : DiffUtil.ItemCallback<Produto>() {
    override fun areItemsTheSame(oldItem: Produto, newItem: Produto): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Produto, newItem: Produto): Boolean {
        return oldItem.id == newItem.id
    }
}