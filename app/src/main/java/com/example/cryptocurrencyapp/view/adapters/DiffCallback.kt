package com.example.cryptocurrencyapp.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem

class DiffCallback : DiffUtil.ItemCallback<AssetsItem>() {
    override fun areItemsTheSame(oldItem: AssetsItem, newItem: AssetsItem): Boolean {
        return oldItem.asset_id == newItem.asset_id
    }

    override fun areContentsTheSame(oldItem: AssetsItem, newItem: AssetsItem): Boolean {
        return oldItem == newItem
    }
}
