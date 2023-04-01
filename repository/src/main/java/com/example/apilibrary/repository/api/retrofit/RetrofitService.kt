package com.example.apilibrary.repository.api.retrofit

import com.example.abstraction.Assets
import com.example.abstraction.AssetCecko
import com.example.apilibrary.repository.const.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET(Constants.LIST_PATH)
    suspend fun getAssets(): Assets
}
