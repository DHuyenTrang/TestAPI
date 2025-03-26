package com.example.testapiapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.testapiapplication.databinding.ItemOsmIdBinding

class ItemListAdapter: ListAdapter<String, ItemListAdapter.ItemViewHolder>(ItemDiffCallBack) {

    inner class ItemViewHolder(private val binding: ItemOsmIdBinding) : ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvOsmId.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemOsmIdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object ItemDiffCallBack: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}
