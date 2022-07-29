package com.example.apilibrary.repository

import androidx.annotation.WorkerThread
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.api.retrofit.RetrofitRequestHelper
import com.example.apilibrary.repository.database.AssetsDao
import kotlinx.coroutines.flow.Flow

class Repository(private val assetsDao: AssetsDao) : IRepository {

    override fun getApiAssets(): IAssetsRequest {
        return RetrofitRequestHelper.getListAssets()
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
