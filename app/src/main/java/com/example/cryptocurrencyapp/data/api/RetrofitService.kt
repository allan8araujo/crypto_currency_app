package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.models.Assets
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.PATH_END_POINT)
     suspend fun getAssets():Assets
}