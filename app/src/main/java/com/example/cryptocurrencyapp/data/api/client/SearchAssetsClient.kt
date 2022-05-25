package com.example.cryptocurrencyapp.data.api.client

import com.example.cryptocurrencyapp.data.api.interceptor.InterceptorSearch
import okhttp3.OkHttpClient

class SearchAssetsClient(searchId: String) {
    val clientInterceptor by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(InterceptorSearch(searchId))
            .build()
    }
}
