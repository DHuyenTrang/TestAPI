package com.example.testapiapplication.ui.reverse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapiapplication.databinding.ItemDataReverseBinding
import com.example.testapiapplication.model.DataReverse

class DataReverseAdapter: ListAdapter<DataReverse, DataReverseAdapter.DataViewHolder>(DataDiffCallBack) {

    inner class DataViewHolder(private val binding: ItemDataReverseBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataReverse) {
            binding.tvPlaceIdValue.text = item.place_id.toString()
            binding.tvDisplayNameValue.text = item.displayName
            binding.tvTurnLanesValue.text = item.turn_lanes
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataReverseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object DataDiffCallBack: DiffUtil.ItemCallback<DataReverse>() {
    override fun areItemsTheSame(oldItem: DataReverse, newItem: DataReverse): Boolean {
        return oldItem.place_id == newItem.place_id
    }

    override fun areContentsTheSame(oldItem: DataReverse, newItem: DataReverse): Boolean {
        return oldItem == newItem
    }

}
