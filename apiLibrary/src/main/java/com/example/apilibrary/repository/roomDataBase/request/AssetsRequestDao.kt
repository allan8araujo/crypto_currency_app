package com.example.apilibrary.repository.roomDataBase.request

import androidx.lifecycle.LiveData
import com.example.apilibrary.repository.roomDataBase.AssetsDAO.AssetsItemDAO
import com.example.apilibrary.repository.roomDataBase.Dao

class AssetsRequestDao(private val dao: Dao) {
    val readAllData: LiveData<List<AssetsItemDAO>> = dao.readAllAssets()

    suspend fun addAsset(asset: AssetsItemDAO) {
        dao.addAsset(asset)
    }
}