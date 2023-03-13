package com.example.cryptocurrencyapp.helper

import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.const.Constants

class UrlHelper {
    fun loadUrlFromGlide(assetItem: AssetsItem): String? {
        return assetItem.id_icon
    }

    fun stringToUrl(idIcon: String): String {
        return Constants.AMAZON_ICON + idIcon.replace("-", "") + ".png"
    }
}
