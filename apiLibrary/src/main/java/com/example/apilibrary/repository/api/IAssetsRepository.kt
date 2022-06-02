package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.response.AssetsDTO.AssetsDTO

interface IAssetsRepository {
    suspend fun getAssets(): AssetsDTO
}
