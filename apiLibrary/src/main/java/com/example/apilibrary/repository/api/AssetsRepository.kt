package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.response.AssetsDTO.AssetsResponse
import com.example.apilibrary.repository.response.AssetsImageDTO.AssetsImageResponse

class AssetsRepository(private val assetsClient: RetrofitService) : IAssetsRepository {
    override suspend fun getAssets(): AssetsResponse {
        return assetsClient.getAssets()
    }

    override suspend fun getIcons(): AssetsImageResponse {
        return assetsClient.getIcons()
    }
}
