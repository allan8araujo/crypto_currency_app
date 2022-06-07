package com.example.apilibrary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.roomDataBase.AssetsDAO.AssetsItemDAO
import com.example.apilibrary.repository.roomDataBase.Dao

interface IRepository {
    fun getApiAssets(): IAssetsRequest
    fun getDatabaseAssets(dao: Dao): LiveData<List<AssetsItemDAO>>
    fun getDatabase(context: Context) : Dao
    suspend fun addAssetOnDatabase(dao: Dao,asset: AssetsItemDAO)
}