package com.example.cryptocurrencyapp.models.assets.AssetsImage

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
