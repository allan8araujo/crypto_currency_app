package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.response.AssetsDTO.AssetsResponse
import com.example.apilibrary.repository.response.AssetsImageDTO.AssetsImageResponse

interface IAssetsRepository {
    suspend fun getAssets(): AssetsResponse
    suspend fun getIcons(): AssetsImageResponse
}
