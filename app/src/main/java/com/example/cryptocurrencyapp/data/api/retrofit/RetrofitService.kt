package com.example.cryptocurrencyapp.data.api.retrofit

import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.models.Assets.Assets
import com.example.cryptocurrencyapp.data.models.AssetsImage.AssetsImage
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.LIST_PATH)
    suspend fun getAssets(): Assets

    @GET(Constants.SEARCH_PATH)
    suspend fun searchAssets(): Assets

    @GET(Constants.ICONS_PATH)
    suspend fun getIcons(): AssetsImage
}
