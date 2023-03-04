package com.example.apilibrary.repository.api.request

import com.example.abstraction.Assets

interface IAssetsRequest {
    suspend fun getAssets(): Assets
}
