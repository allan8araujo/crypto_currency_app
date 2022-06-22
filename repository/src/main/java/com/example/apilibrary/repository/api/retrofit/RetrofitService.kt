package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.const.Constants
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.LIST_PATH)
    suspend fun getAssets(): com.example.abstraction.Assets
}
