package com.example.cryptocurrencyapp.presentation.ui.coindetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.abstraction.AssetsItem

class CoinDetailSharedViewModel : ViewModel() {
    var selectedCoin by mutableStateOf<AssetsItem?>(null)
        private set

    var isFavoriteAsset by mutableStateOf(false)
        private set

    fun addCoin(newSelectedCoin: AssetsItem) {
        selectedCoin = newSelectedCoin
    }

    fun setIsFavorite(isFavorite: Boolean) {
        isFavoriteAsset = isFavorite
    }
}
