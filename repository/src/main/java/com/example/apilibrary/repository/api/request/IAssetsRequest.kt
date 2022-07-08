package com.example.apilibrary.repository.api.request

interface IAssetsRequest {
    suspend fun getAssets(): com.example.abstraction.Assets
}
