package com.example.cryptocurrencyapp.utils

import com.example.cryptocurrencyapp.helper.UrlHelper
import java.text.NumberFormat
import java.util.*

fun String.toAssetsImage(): String = UrlHelper().stringToUrl(this)

fun Double.toMoneyFormat(): String? {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 5
    format.currency = Currency.getInstance("EUR")

    return format.format(this)
}