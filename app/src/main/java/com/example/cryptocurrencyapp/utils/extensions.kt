package com.example.cryptocurrencyapp.utils

import com.example.cryptocurrencyapp.helper.UrlHelper

fun toAssetsImage(idIcon: String?): String? {
    idIcon?.let {
        return UrlHelper().stringToUrl(idIcon)
    }
    return null
}