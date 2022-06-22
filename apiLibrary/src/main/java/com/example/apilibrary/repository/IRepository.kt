package com.example.apilibrary.repository

import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.api.request.IAssetsRequest
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getApiAssets(): IAssetsRequest
    suspend fun insertAsset(asset: AssetsItem)
    suspend fun deleteAsset(asset: AssetsItem)
}
