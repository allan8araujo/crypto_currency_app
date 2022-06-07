package com.example.apilibrary.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.apilibrary.repository.api.retrofit.RetrofitRequestHelper
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.roomDataBase.AssetsDAO.AssetsItemDAO
import com.example.apilibrary.repository.roomDataBase.AssetsDatabase
import com.example.apilibrary.repository.roomDataBase.Dao
import com.example.apilibrary.repository.roomDataBase.request.AssetsRequestDao

class Repository(): IRepository {
    override fun getApiAssets(): IAssetsRequest {
        return RetrofitRequestHelper.getListAssets()
    }

    override fun getDatabaseAssets(dao: Dao): LiveData<List<AssetsItemDAO>> {
        return AssetsRequestDao(dao).readAllData
    }

    override fun getDatabase(context: Context): Dao {
        return AssetsDatabase.getDatabase(context).assetDao()
    }

    override suspend fun addAssetOnDatabase(dao: Dao, asset: AssetsItemDAO) {
        AssetsRequestDao(dao).addAsset(asset)
    }


}