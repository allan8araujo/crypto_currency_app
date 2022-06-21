package com.example.apilibrary.repository

import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.api.request.IAssetsRequest
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getApiAssets(): IAssetsRequest
    fun getAssets(): Flow<List<AssetsItem>>
    suspend fun insertAsset(asset: AssetsItem)
    suspend fun deleteAsset(asset: AssetsItem)
}
