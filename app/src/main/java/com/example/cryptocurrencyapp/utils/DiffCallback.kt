package com.example.cryptocurrencyapp.utils

import androidx.recyclerview.widget.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<com.example.abstraction.AssetsItem>() {
    override fun areItemsTheSame(
        oldItem: com.example.abstraction.AssetsItem,
        newItem: com.example.abstraction.AssetsItem,
    ): Boolean {
        return oldItem.asset_id == newItem.asset_id
    }

    override fun areContentsTheSame(
        oldItem: com.example.abstraction.AssetsItem,
        newItem: com.example.abstraction.AssetsItem,
    ): Boolean {
        return oldItem == newItem
    }
}
