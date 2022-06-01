package com.example.apilibrary.repository.api.interceptor

import com.example.apilibrary.repository.api.IAssetsRepository
import com.example.apilibrary.repository.api.retrofit.RetrofitInstance
import okhttp3.OkHttpClient

class InterceptorHelper {
    fun interceptorHelper(interceptor: OkHttpClient): IAssetsRepository {
        return RetrofitInstance().retrofitInstance(interceptor)
    }
}
