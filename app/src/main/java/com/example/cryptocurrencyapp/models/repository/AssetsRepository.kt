package com.example.cryptocurrencyapp.models.repository

import com.example.cryptocurrencyapp.models.repository.api.retrofit.RetrofitService
import com.example.cryptocurrencyapp.models.assets.Assets.Assets
import com.example.cryptocurrencyapp.models.assets.AssetsImage.AssetsImage

class AssetsRepository(private val assetsClient: RetrofitService) : IAssetsRepository {
    override suspend fun getAssets(): Assets {
        return assetsClient.getAssets()
    }

    override suspend fun getIcons(): AssetsImage {
        return assetsClient.getIcons()
    }
}
