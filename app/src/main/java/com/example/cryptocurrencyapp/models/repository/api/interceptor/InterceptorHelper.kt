package com.example.cryptocurrencyapp.models.repository.api.interceptor

import com.example.cryptocurrencyapp.models.repository.api.retrofit.RetrofitInstance
import com.example.cryptocurrencyapp.models.repository.AssetsRepository
import okhttp3.OkHttpClient

class InterceptorHelper {
    fun interceptorHelper(interceptor: OkHttpClient): AssetsRepository {
        return RetrofitInstance().retrofitInstance(interceptor)
    }
}
