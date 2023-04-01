package com.example.apilibrary.repository

import com.example.abstraction.AssetCecko
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.database.AssetsDao
import com.example.apilibrary.repository.states.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class Repository(private val assetsDao: AssetsDao, private val repository: RetrofitService) : IRepository {
    override fun getApiAssets(): Flow<DataResult<Assets>> {
        return flow {
            emit(DataResult.Loading)
            val result = repository.getAssets()
            emit(DataResult.Success(result))
        }.catch {
            emit(DataResult.Error(it, emptyList()))
        }
    }
    fun getAllAssets(): Flow<List<AssetsItem>> {
        return assetsDao.getFavoriteAssets()
    }

    override suspend fun insertFavoriteAsset(asset: AssetsItem) {
        assetsDao.insertFavorite(asset)
    }

    override suspend fun deleteFavoriteAsset(asset: AssetsItem) {
        assetsDao.deleteFavorite(asset)
    }
}
