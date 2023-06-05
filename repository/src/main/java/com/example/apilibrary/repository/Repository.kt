package com.example.apilibrary.repository

import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.util.DataResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getApiAssets(): Flow<DataResult<List<AssetsItem>>>
    suspend fun insertFavoriteAssetToDatabase(asset: AssetsItem)
    suspend fun deleteFavoriteAssetFromDatabase(asset: AssetsItem)
}
