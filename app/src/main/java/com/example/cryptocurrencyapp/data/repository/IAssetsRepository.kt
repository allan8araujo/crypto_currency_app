package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.models.Assets.Assets
import com.example.cryptocurrencyapp.data.models.AssetsImage.AssetsImage

interface IAssetsRepository {
    suspend fun getAssets(): Assets
    suspend fun getIcons(): AssetsImage
}
