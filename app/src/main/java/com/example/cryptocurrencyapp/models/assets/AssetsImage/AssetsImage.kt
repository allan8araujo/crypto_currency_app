package com.example.cryptocurrencyapp.models.assets.AssetsImage

import com.example.apilibrary.repository.assets.AssetsImage.AssetsImageItem

class AssetsImage : ArrayList<AssetsImageItem>()

val emptyImageList = arrayListOf(
    AssetsImageItem(
        asset_id = "",
        url = ""
    ),
    AssetsImageItem(
        asset_id = "",
        url = ""
    ),
    AssetsImageItem(
        asset_id = "",
        url = ""
    ),
    AssetsImageItem(
        asset_id = "",
        url = ""
    ),
    AssetsImageItem(
        asset_id = "",
        url = ""
    )
)
fun funEmptyImageList() = emptyImageList
