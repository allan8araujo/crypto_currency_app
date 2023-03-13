package com.example.cryptocurrencyapp.utils

sealed class FilterEnum(val type: String) {

    companion object {
        const val CRYPTO_CURRENCIES = "crypto currencies"
        const val TRADITIONAL_CURRENCIES = "traditional currencies"
        const val ALL_CURRENCIES = "all currencies"
    }

    object cryptoCurrencies : FilterEnum(CRYPTO_CURRENCIES)
    object traditionalCurrencies : FilterEnum(TRADITIONAL_CURRENCIES)
    object allCurrencies : FilterEnum(ALL_CURRENCIES)
}