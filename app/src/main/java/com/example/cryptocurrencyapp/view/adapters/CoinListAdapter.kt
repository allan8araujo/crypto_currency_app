package com.example.cryptocurrencyapp.view.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.ItemCoinBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinListAdapter(
    var iconViewModel: AssetsListViewModel,
    var onClick: (asset: AssetsItem) -> Unit = {},
) :
    ListAdapter<AssetsItem, CoinListAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun loadUrlFromGlide(assetItem: AssetsItem): String? {
            val assetUrlLink = iconViewModel.icon.value?.find {
                it.asset_id == assetItem.asset_id
            }
            return assetUrlLink?.url
        }

        fun bind(assetItem: AssetsItem) {
            val progressBar = binding.pbLoading
            progressBar.visibility = View.VISIBLE
            Glide.with(binding.root)
                .load(loadUrlFromGlide(assetItem))
                .placeholder(R.drawable.ic_coin_base)
                .listener(ProgressBarListener(progressBar))
                .centerCrop()
                .into(binding.coinIconImageView)

            with(binding) {
//                coinIconImageView.setBackgroundResource(R.drawable.ic_coin_base)
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
