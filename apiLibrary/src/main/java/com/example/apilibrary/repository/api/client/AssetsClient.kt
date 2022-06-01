package com.example.apilibrary.repository.api.client

import com.example.apilibrary.repository.api.interceptor.InterceptorList
import okhttp3.OkHttpClient

class AssetsClient {
    val clientInterceptor by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(InterceptorList())
            .build()
    }
}
