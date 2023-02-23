package com.example.apilibrary.repository

import androidx.annotation.WorkerThread
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.api.retrofit.RetrofitRequestHelper
import com.example.apilibrary.repository.database.AssetsDao
import com.example.apilibrary.repository.states.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Repository(private val assetsDao: AssetsDao) : IRepository {
    override fun getApiAssets(): Flow<DataResult<Assets>> {
        return flow {
            emit(DataResult.Loading)
            val result = RetrofitRequestHelper.getListAssets()
            emit(DataResult.Success(result.getAssets()))
        }.catch {
            emit(DataResult.Error(it, emptyList()))
        }
    }

    val getAllAssets: Flow<List<AssetsItem>> = assetsDao.getFavoriteAssets()

    @WorkerThread
    override suspend fun insertFavoriteAsset(asset: AssetsItem) {
        assetsDao.insertFavorite(asset)
    }

    @WorkerThread
    override suspend fun deleteFavoriteAsset(asset: AssetsItem) {
        assetsDao.deleteFavorite(asset)
    }
}
