package com.example.cryptocurrencyapp.commons.utils

import com.example.abstraction.AssetsItem
import java.text.NumberFormat
import java.util.Currency

fun String.toAssetsImage(): String = UrlHelper().stringToUrl(this)

fun Double.toMoneyFormat(): String? {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 5
    format.currency = Currency.getInstance("EUR")

    return format.format(this)
}

fun AssetsItem.formatDisplayedText() =
    if (price_usd != null) price_usd!!.toMoneyFormat()
        .toString() else "price is unavailable."