package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.models.Assets
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.LIST_PATH_END_POINT)
    suspend fun getAssets(): Assets

    @GET(Constants.SEARCH_PATH_END_POINT)
    suspend fun searchAssets(): Assets
}
