package com.example.cryptocurrencyapp.data.api.interceptor

import com.example.cryptocurrencyapp.data.api.retrofit.RetrofitInstance
import com.example.cryptocurrencyapp.data.repository.AssetsRepository
import okhttp3.OkHttpClient

class InterceptorHelper {
    fun interceptorHelper(interceptor: OkHttpClient): AssetsRepository {
        return RetrofitInstance().retrofitInstance(interceptor)
    }
}

