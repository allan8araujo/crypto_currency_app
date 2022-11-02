package com.example.cryptocurrencyapp.viewmodel

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.helper.UrlHelper
import com.example.cryptocurrencyapp.utils.ProgressBarListener

class CoinDetailsViewModel() : ViewModel() {

    fun settingPricesAndVolum(
        binding: DetailsFragmentBinding,
        asset: AssetsItem,
        price_usd: Double?,
    ) {
        with(binding) {
            if (asset.price_usd != null) {
                priceUsdTextView.text = price_usd.toString()
            } else {
                priceUsdTextView.setEms(5)
                priceUsdTextView.setCompoundDrawablesRelative(null, null, null, null)
                priceUsdTextView.text = "Indisponivel"
            }
            lastHourValueTextView.text = asset.volume_1hrs_usd.toString()
            lastWeekValueTextView.text = asset.volume_1day_usd.toString()
            lastMounthValueTextView.text = asset.volume_1mth_usd.toString()
        }
    }

    fun settingImageIcon(
        asset: AssetsItem,
        binding: DetailsFragmentBinding,
    ) {
        val progressBar = binding.detailsProgressBar
        progressBar.isVisible = true
        Glide.with(binding.root)
            .load(UrlHelper().loadUrlFromGlide(asset))
            .placeholder(R.drawable.ic_coin_base)
            .listener(ProgressBarListener(progressBar))
            .centerCrop()
            .into(binding.coinIconImageView)
    }
}
