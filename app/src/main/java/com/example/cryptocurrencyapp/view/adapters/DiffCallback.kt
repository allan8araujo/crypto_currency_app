package com.example.cryptocurrencyapp.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrencyapp.data.models.Assets
import com.example.cryptocurrencyapp.data.models.AssetsItem

class DiffCallback : DiffUtil.ItemCallback<AssetsItem>() {
    override fun areItemsTheSame(oldItem: AssetsItem, newItem: AssetsItem): Boolean {
        return oldItem.asset_id == newItem.asset_id
    }

    override fun areContentsTheSame(oldItem: AssetsItem, newItem: AssetsItem): Boolean {
        return oldItem == newItem
    }

}