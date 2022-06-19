package com.example.apilibrary.repository

import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.database.TinyDB

interface IRepository {
    fun getApiAssets(): IAssetsRequest
    fun getDatabaseAssets(): TinyDB
}
