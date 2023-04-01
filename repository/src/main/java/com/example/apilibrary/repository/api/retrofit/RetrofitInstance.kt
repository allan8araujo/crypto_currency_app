package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.api.interceptor.InterceptorList
import com.example.apilibrary.repository.const.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinApiInstance {
    companion object {
        fun getCryptoRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .client(
                    OkHttpClient
                        .Builder()
                        .addInterceptor(InterceptorList())
                        .build()
                )
                .baseUrl(Constants.PATH_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
