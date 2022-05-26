package com.example.cryptocurrencyapp.data.api.retrofit

import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.repository.AssetsRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance() {
    fun retrofitInstance(interpertor: OkHttpClient): AssetsRepository {
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
        return AssetsRepository(retrofitClient)
    }
}