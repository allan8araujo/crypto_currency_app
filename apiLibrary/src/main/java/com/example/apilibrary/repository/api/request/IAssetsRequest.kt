package com.example.apilibrary.repository.api.request

import com.example.apilibrary.repository.response.AssetsDTO.AssetsDTO

interface IAssetsRequest {
    suspend fun getAssets(): AssetsDTO
}
