package com.example.apilibrary.repository

import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.states.DataResult
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getApiAssets(): Flow<DataResult<Assets>>
    suspend fun insertFavoriteAsset(asset: AssetsItem)
    suspend fun deleteFavoriteAsset(asset: AssetsItem)
}
