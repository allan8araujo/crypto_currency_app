package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.api.client.AssetsClient
import com.example.apilibrary.repository.api.interceptor.InterceptorHelper
import com.example.apilibrary.repository.api.request.IAssetsRequest

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): IAssetsRequest {
            return (InterceptorHelper().interceptorHelper(
                AssetsClient().clientInterceptor
            ))
        }
    }
}
