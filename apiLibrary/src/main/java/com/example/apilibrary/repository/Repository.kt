package com.example.apilibrary.repository

import android.content.Context
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.api.retrofit.RetrofitRequestHelper
import com.example.apilibrary.repository.database.TinyDB

class Repository(private val context: Context): IRepository {
    override fun getApiAssets(): IAssetsRequest {
        return RetrofitRequestHelper.getListAssets()
    }

    override fun getDatabaseAssets(): TinyDB {
        return TinyDB(context)
    }

}