package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.api.IAssetsRepository
import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.assets.Assets.Assets
import com.example.apilibrary.repository.assets.AssetsImage.AssetsImage

class AssetsRepository(private val assetsClient: RetrofitService) : IAssetsRepository {
    override suspend fun getAssets(): Assets {
        return assetsClient.getAssets()
    }

    override suspend fun getIcons(): AssetsImage {
        return assetsClient.getIcons()
    }
}
