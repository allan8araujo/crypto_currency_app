package com.example.cryptocurrencyapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ItemCoinBinding
import com.example.cryptocurrencyapp.helper.UrlHelper
import com.example.cryptocurrencyapp.utils.DiffCallback
import com.example.cryptocurrencyapp.utils.ProgressBarListener
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinListAdapter(
    val context: LifecycleOwner,
    var coinViewModel: AssetsListViewModel,
    var onClick: (asset: AssetsItem) -> Unit = {},
) :
    ListAdapter<AssetsItem, CoinListAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(assetItem: AssetsItem) {

            val progressBar = binding.pbLoading
            progressBar.visibility = View.VISIBLE
            Glide.with(binding.root)
                .load(UrlHelper().loadUrlFromGlide(assetItem))
                .placeholder(R.drawable.ic_coin_base)
                .listener(ProgressBarListener(progressBar))
                .centerCrop()
                .into(binding.coinIconImageView)

            with(binding) {
                coinViewModel.allFavoriteAssets.observe(context) { assetsItensList ->
                    val findByAssetID =
                        assetsItensList?.any { assetItem_ ->
                            assetItem_.asset_id == assetItem.asset_id
                        }
                    if (findByAssetID == true) {
                        favoriteImageView.visibility = View.VISIBLE
                    } else {
                        favoriteImageView.visibility = View.GONE
                    }
                }
                coinAssetIdTextView.text = assetItem.asset_id
                coinNameTextView.text = assetItem.name
                priceUsdTextView.text = if (assetItem.price_usd != null) {
                    assetItem.price_usd.toString()
                } else {
                    priceUsdTextView.setEms(5)
                    priceUsdTextView.setCompoundDrawablesRelative(null, null, null, null)
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
