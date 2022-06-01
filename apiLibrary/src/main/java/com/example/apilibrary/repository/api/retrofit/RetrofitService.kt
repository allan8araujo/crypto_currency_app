package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.const.Constants
import com.example.apilibrary.repository.assets.Assets.Assets
import com.example.apilibrary.repository.assets.AssetsImage.AssetsImage
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.LIST_PATH)
    suspend fun getAssets(): Assets

    @GET(Constants.ICONS_PATH)
    suspend fun getIcons(): AssetsImage
}
