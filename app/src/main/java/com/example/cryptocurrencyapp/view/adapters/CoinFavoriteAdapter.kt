package com.example.cryptocurrencyapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.ItemFavoriteBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinFavoriteAdapter(
    var context: Context,
    var iconViewModel: AssetsListViewModel,
    var onClick: (asset: AssetsItem) -> Unit = {},
) :
    ListAdapter<AssetsItem, CoinFavoriteAdapter.ViewHolder>(DiffCallback()) {


    inner class ViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun loadUrlFromGlide(assetItem: AssetsItem): String? {
            val assetUrlLink = iconViewModel.icon.value?.find {
                it.asset_id == assetItem.asset_id
            }
            return assetUrlLink?.url
        }

        fun bind(assetItem: AssetsItem) {

            val progressBar = binding.favoriteProgressBar
            progressBar.visibility = View.VISIBLE
            Glide.with(binding.root)
                .load(loadUrlFromGlide(assetItem))
                .placeholder(R.drawable.ic_coin_base)
                .listener(ProgressBarListener(progressBar))
                .centerCrop()
                .into(binding.imgCoinImageView)

            with(binding) {

                nameBitcoinTextView.text = assetItem.name
                btcTextView.text = assetItem.asset_id
                if (assetItem.price_usd != null) {
                    priceBitCoinTextView.text = assetItem.price_usd.toString()
                } else {
                    priceBitCoinTextView.text = "0.00"
                }

                cardImageView.setOnClickListener { onClick.invoke(assetItem) }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}