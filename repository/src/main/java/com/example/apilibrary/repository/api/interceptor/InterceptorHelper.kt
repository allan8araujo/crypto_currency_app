package com.example.apilibrary.repository.api.interceptor

import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.api.retrofit.RetrofitInstance
import okhttp3.OkHttpClient

class InterceptorHelper {
    fun interceptorHelper(interceptor: OkHttpClient): IAssetsRequest {
        return RetrofitInstance()
            .retrofitInstance(interceptor)
    }
}
