package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.const.Constants
import com.example.apilibrary.repository.response.AssetsDTO.AssetsResponse
import com.example.apilibrary.repository.response.AssetsImageDTO.AssetsImageResponse
import retrofit2.http.GET

interface RetrofitService {
    @GET(Constants.LIST_PATH)
    suspend fun getAssets(): AssetsResponse

    @GET(Constants.ICONS_PATH)
    suspend fun getIcons(): AssetsImageResponse
}
