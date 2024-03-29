package com.example.cryptocurrencyapp.presentation.ui.coinlist

import com.example.abstraction.AssetsItem

data class CoinListState(
    val isLoading: Boolean = false,
    var isSucess: List<AssetsItem> = emptyList(),
    val isError: String? = ""
)
