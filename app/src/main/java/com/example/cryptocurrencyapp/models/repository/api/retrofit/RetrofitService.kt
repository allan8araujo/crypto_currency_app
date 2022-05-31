package com.example.cryptocurrencyapp.models.repository.api.retrofit

import com.example.cryptocurrencyapp.models.const.Constants
import com.example.cryptocurrencyapp.models.assets.Assets.Assets
import com.example.cryptocurrencyapp.models.assets.AssetsImage.AssetsImage
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.LIST_PATH)
    suspend fun getAssets(): Assets
    @GET(Constants.ICONS_PATH)
    suspend fun getIcons(): AssetsImage
}
