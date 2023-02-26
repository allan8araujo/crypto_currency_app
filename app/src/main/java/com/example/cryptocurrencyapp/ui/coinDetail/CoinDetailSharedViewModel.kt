package com.example.cryptocurrencyapp.ui.coinDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.abstraction.AssetsItem

class CoinDetailSharedViewModel : ViewModel() {
    var selectedCoin by mutableStateOf<AssetsItem?>(null)
        private set

    fun addCoin(newSelectedCoin: AssetsItem) {
        selectedCoin = newSelectedCoin
    }
}