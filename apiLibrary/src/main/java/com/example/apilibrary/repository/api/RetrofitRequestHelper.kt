package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.api.client.AssetsClient
import com.example.apilibrary.repository.api.interceptor.InterceptorHelper

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): IAssetsRepository {
            return (InterceptorHelper().interceptorHelper(AssetsClient().clientInterceptor))
        }
    }
}
