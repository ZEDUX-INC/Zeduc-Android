package com.zedux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zedux.R
import com.zedux.databinding.ItemRecentBinding

class RecentAdapter: RecyclerView.Adapter<RecentAdapter.RecentItemViewHolder>() {

    val diffCallback = object: DiffUtil.ItemCallback<String>() {
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<String>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemRecentBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recent,
            parent,
            false)

        return RecentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentItemViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RecentItemViewHolder(binding: ItemRecentBinding): RecyclerView.ViewHolder(binding.root) {

    }
}