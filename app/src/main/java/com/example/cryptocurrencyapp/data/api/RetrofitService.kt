package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.models.Assets
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.PATH_URL_BASE)
    suspend fun getAssets():Assets
}