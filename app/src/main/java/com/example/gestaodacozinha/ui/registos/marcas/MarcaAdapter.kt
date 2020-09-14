package com.example.gestaodacozinha.ui.registos.marcas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gestaodacozinha.data.registos.Marca
import com.example.gestaodacozinha.databinding.ListItemMarcaBinding

class MarcaAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Marca, MarcaAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder private constructor(private val binding: ListItemMarcaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMarcaBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Marca, clickListener: OnClickListener) {
            binding.marca = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Marca>() {
        override fun areItemsTheSame(oldItem: Marca, newItem: Marca): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Marca, newItem: Marca): Boolean {
            return oldItem.nome == newItem.nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }

    class OnClickListener(val clickListener: (marca: Marca) -> Unit) {
        fun onClick(marca: Marca) = clickListener(marca)
    }
}