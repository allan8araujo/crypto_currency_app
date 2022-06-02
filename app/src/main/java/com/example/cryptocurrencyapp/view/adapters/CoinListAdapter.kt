package com.example.cryptocurrencyapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ItemCoinBinding
import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinListAdapter(
    val context: Context,
    var iconViewModel: AssetsListViewModel,
    var onClick: (asset: AssetsItem) -> Unit = {},
) :
    ListAdapter<AssetsItem, CoinListAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(assetItem: AssetsItem) {
            val dataBase = TinyDB(context)
            val progressBar = binding.pbLoading
            progressBar.visibility = View.VISIBLE
            Glide.with(binding.root)
                .load(iconViewModel.loadUrlFromGlide(assetItem))
                .placeholder(R.drawable.ic_coin_base)
                .listener(ProgressBarListener(progressBar))
                .centerCrop()
                .into(binding.coinIconImageView)

            with(binding) {

                if (dataBase.hasItem(assetItem.asset_id)) {
                    favoriteImageView.visibility = View.VISIBLE
                }

                coinAssetIdTextView.text = assetItem.asset_id
                coinNameTextView.text = assetItem.name
                priceUsdTextView.text = if (assetItem.price_usd != null) {
                    assetItem.price_usd.toString()
                } else {
                    priceUsdTextView.setEms(5)
                    dolarIconImageView.visibility = View.GONE
                    "Indisponivel"
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
