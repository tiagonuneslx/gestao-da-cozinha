package com.example.gestaodacozinha.ui.registos.produtoQuantidades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gestaodacozinha.data.registos.ProdutoQuantidade
import com.example.gestaodacozinha.databinding.ListItemProdutoQuantidadeBinding

class ProdutoQuantidadeAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProdutoQuantidade, ProdutoQuantidadeAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder private constructor(private val binding: ListItemProdutoQuantidadeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemProdutoQuantidadeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: ProdutoQuantidade, clickListener: OnClickListener) {
            binding.produtoQuantidade = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProdutoQuantidade>() {
        override fun areItemsTheSame(
            oldItem: ProdutoQuantidade,
            newItem: ProdutoQuantidade
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ProdutoQuantidade,
            newItem: ProdutoQuantidade
        ): Boolean {
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

    class OnClickListener(val clickListener: (produtoQuantidade: ProdutoQuantidade, view: View) -> Unit) {
        fun onClick(produtoQuantidade: ProdutoQuantidade, view: View) =
            clickListener(produtoQuantidade, view)
    }
}