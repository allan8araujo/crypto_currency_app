package com.example.cryptocurrencyapp.data.api.client

import com.example.cryptocurrencyapp.data.api.interceptor.InterceptorList
import okhttp3.OkHttpClient

class AssetsClient {
    val clientInterceptor by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(InterceptorList())
            .build()
    }
}
