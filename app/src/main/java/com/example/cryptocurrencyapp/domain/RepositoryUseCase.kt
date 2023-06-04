package com.example.cryptocurrencyapp.domain

import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.util.DataResult
import kotlinx.coroutines.flow.Flow

class RepositoryUseCase:IRepositoryUseCase {
    override fun getApiAssets(): Flow<DataResult<List<AssetsItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavoriteAssetToDatabase(asset: AssetsItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteAssetFromDatabase(asset: AssetsItem) {
        TODO("Not yet implemented")
    }
}
