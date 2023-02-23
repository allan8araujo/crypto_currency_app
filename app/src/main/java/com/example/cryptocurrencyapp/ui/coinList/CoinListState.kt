package com.example.cryptocurrencyapp.ui.coinList

data class CoinListState(
    val isLoading: Boolean = false,
    var isSucess: Boolean = false,
    val isError: String? = ""
)
