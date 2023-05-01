package com.example.apilibrary.repository.const

import java.text.SimpleDateFormat
import java.util.Date

class Constants {
    companion object {
        //COIN API
        val DATE_NOW = SimpleDateFormat("dd MMM yyyy").format(Date())
        const val PATH_URL_BASE = "https://rest.coinapi.io/"
        const val LIST_PATH = "v1/assets"
        const val ICONS_PATH = "v1/assets/icons/128"
        const val AMAZON_ICON =
            "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/"
        var API_KEY = "E1483ECB-B918-4AC5-AE2D-628604676A17"
    }
}
