package com.example.apilibrary.repository.response.AssetsImageDTO

class AssetsImageResponse : ArrayList<AssetsImageItemDTO>()

val emptyImageList = arrayListOf(
    AssetsImageItemDTO(
        asset_id = "",
        url = ""
    ),
    AssetsImageItemDTO(
        asset_id = "",
        url = ""
    ),
    AssetsImageItemDTO(
        asset_id = "",
        url = ""
    ),
    AssetsImageItemDTO(
        asset_id = "",
        url = ""
    ),
    AssetsImageItemDTO(
        asset_id = "",
        url = ""
    )
)

fun funEmptyImageList() = emptyImageList
