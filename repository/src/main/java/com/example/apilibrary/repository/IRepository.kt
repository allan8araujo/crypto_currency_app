package com.example.apilibrary.repository

import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.states.DataResult
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getApiAssets(): Flow<DataResult<List<AssetsItem>>>
    suspend fun insertFavoriteAssetToDatabase(asset: AssetsItem)
    suspend fun deleteFavoriteAssetFromDatabase(asset: AssetsItem)
}
