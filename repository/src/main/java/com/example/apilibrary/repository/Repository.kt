package com.example.apilibrary.repository

import com.example.abstraction.AssetsItem
import com.example.abstraction.toAssets
import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.database.AssetsDao
import com.example.apilibrary.repository.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Repository(private val assetsDao: AssetsDao, private val repository: RetrofitService) :
    IRepository {

    /**
     * Returns a Flow with API call results
     * */

    override fun getApiAssets(): Flow<DataResult<List<AssetsItem>>> {
        return flow {
            emit(DataResult.Loading)
            val result = repository.getAssets().toAssets()
            emit(DataResult.Success(result))
        }.catch {
            emit(DataResult.Error(it, emptyList()))
        }
    }

    /**
     * Returns a Flow with database call results
     * */

    fun getFavoriteAssetsFromDatabase(): Flow<List<AssetsItem>> {
        return assetsDao.getFavoriteAssets()
    }

    /**
     *  Inserts an asset into the database
     * */

    override suspend fun insertFavoriteAssetToDatabase(asset: AssetsItem) {
        assetsDao.insertFavoriteAssetToDatabase(asset)
    }

    /**
     *  Deletes an asset from the database
     * */

    override suspend fun deleteFavoriteAssetFromDatabase(asset: AssetsItem) {
        assetsDao.deleteFavoriteAssetFromDatabase(asset)
    }
}
