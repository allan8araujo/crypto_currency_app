package com.example.apilibrary.repository.api.request

import com.example.abstraction.Assets
import com.example.apilibrary.repository.api.retrofit.RetrofitService

class AssetsRequest(private val assetsClient: RetrofitService) : IAssetsRequest {
    override suspend fun getAssets(): Assets {
        return assetsClient.getAssets()
    }
}
