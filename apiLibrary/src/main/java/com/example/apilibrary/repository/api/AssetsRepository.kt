package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.response.AssetsDTO.AssetsDTO

class AssetsRepository(private val assetsClient: RetrofitService) : IAssetsRepository {
    override suspend fun getAssets(): AssetsDTO {
        return assetsClient.getAssets()
    }
}
