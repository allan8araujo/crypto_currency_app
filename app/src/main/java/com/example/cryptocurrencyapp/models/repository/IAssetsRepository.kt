package com.example.cryptocurrencyapp.models.repository

import com.example.cryptocurrencyapp.models.assets.Assets.Assets
import com.example.cryptocurrencyapp.models.assets.AssetsImage.AssetsImage

interface IAssetsRepository {
    suspend fun getAssets(): Assets
    suspend fun getIcons(): AssetsImage
}
