package com.example.gestaodacozinha.ui.produtos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gestaodacozinha.data.Produto
import com.example.gestaodacozinha.databinding.ListItemProdutoBinding

class ProdutoAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Produto, ProdutoAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder private constructor(private val binding: ListItemProdutoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemProdutoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Produto, clickListener: OnClickListener) {
            binding.produto = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Produto>() {
        override fun areItemsTheSame(oldItem: Produto, newItem: Produto): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Produto, newItem: Produto): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }

    class OnClickListener(val clickListener: (produto: Produto) -> Unit) {
        fun onClick(produto: Produto) = clickListener(produto)
    }
}