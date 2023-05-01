package com.example.cryptocurrencyapp.commons.utils

import com.example.apilibrary.repository.const.Constants

class UrlHelper {
    fun stringToUrl(idIcon: String): String {
        return Constants.AMAZON_ICON + idIcon.replace("-", "") + ".png"
    }
}
