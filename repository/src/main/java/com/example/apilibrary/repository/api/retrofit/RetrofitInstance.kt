package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.api.request.AssetsRequest
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.const.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    fun retrofitInstance(interpertor: OkHttpClient): IAssetsRequest {
        val retrofitInstance by lazy {
            Retrofit
                .Builder()
                .client(interpertor)
                .baseUrl(Constants.PATH_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val retrofitClient: RetrofitService by lazy {
            retrofitInstance.create(RetrofitService::class.java)
        }
        return AssetsRequest(retrofitClient)
    }
}
