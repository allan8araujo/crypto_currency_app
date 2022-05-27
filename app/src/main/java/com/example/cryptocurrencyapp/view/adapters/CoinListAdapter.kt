package com.example.cryptocurrencyapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.ItemCoinBinding

class CoinListAdapter(val context: Context, var onClick: (asset: AssetsItem) -> Unit = {}) :
    ListAdapter<AssetsItem, CoinListAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(assetItem: AssetsItem) {
            val dataBase = TinyDB(context)
            with(binding) {

                if (dataBase.hasItem(assetItem.asset_id)) {
                    favoriteImageView.visibility = View.VISIBLE
                }

                coinIconImageView.setBackgroundResource(R.drawable.ic_coin_base)
                coinAssetIdTextView.text = assetItem.asset_id
                coinNameTextView.text = assetItem.name
                priceUsdTextView.text = if (assetItem.price_usd != null) {
                    assetItem.price_usd.toString()
                } else {
                    "0.00"
                }
                digitalCoinCardView.setOnClickListener { onClick.invoke(assetItem) }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}
