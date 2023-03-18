package com.example.cryptocurrencyapp.ui.coinList

import com.example.abstraction.AssetsItem

data class CoinListState(
    val isLoading: Boolean = false,
    var isSucess: List<AssetsItem> = emptyList(),
    val isError: String? = ""
)
