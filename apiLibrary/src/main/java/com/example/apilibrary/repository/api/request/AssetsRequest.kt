package com.example.apilibrary.repository.api.request

import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.response.AssetsDTO.AssetsDTO

class AssetsRequest(private val assetsClient: RetrofitService) : IAssetsRequest {
    override suspend fun getAssets(): AssetsDTO {
        return assetsClient.getAssets()
    }
}
