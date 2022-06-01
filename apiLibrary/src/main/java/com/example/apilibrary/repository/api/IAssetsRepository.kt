package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.assets.Assets.Assets
import com.example.apilibrary.repository.assets.AssetsImage.AssetsImage

interface IAssetsRepository {
    suspend fun getAssets(): Assets
    suspend fun getIcons(): AssetsImage
}
