package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.response.AssetsImageDTO.AssetsImageResponse
import com.example.apilibrary.repository.response.AssetsDTO.AssetsResponse

interface IAssetsRepository {
    suspend fun getAssets(): AssetsResponse
    suspend fun getIcons(): AssetsImageResponse
}
