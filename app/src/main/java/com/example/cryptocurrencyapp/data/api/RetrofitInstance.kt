package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.data.AssetsViewModelFactory
import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.repository.AssetsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        val retrofitInstance by lazy {
            Retrofit
                .Builder()
                .client(HttpClient.clientInterceptor)
                .baseUrl(Constants.PATH_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val retrofitClient: RetrofitService by lazy {
            retrofitInstance.create(RetrofitService::class.java)
        }

        val assetsRespository = AssetsRepository(retrofitClient)
        val assetsFactory = AssetsViewModelFactory(assetsRespository)

    }
}