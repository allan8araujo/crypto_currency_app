package com.example.cryptocurrencyapp.models.repository.api.interceptor

import com.example.cryptocurrencyapp.models.repository.AssetsRepository
import com.example.cryptocurrencyapp.models.repository.api.retrofit.RetrofitInstance
import okhttp3.OkHttpClient

class InterceptorHelper {
    fun interceptorHelper(interceptor: OkHttpClient): AssetsRepository {
        return RetrofitInstance().retrofitInstance(interceptor)
    }
}
