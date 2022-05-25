package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.api.RetrofitService
import com.example.cryptocurrencyapp.data.models.Assets

class AssetsRepository(private val assetsClient: RetrofitService) : IAssetsRepository {
    override suspend fun getAssets(): Assets {
        return assetsClient.getAssets()
    }

    override suspend fun searchAsset(): Assets {
        return assetsClient.searchAssets()
    }
}
