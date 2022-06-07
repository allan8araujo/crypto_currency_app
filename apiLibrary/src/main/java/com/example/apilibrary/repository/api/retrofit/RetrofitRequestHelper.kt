package com.example.apilibrary.repository.api.retrofit

import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.api.client.AssetsClient
import com.example.apilibrary.repository.api.interceptor.InterceptorHelper

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): IAssetsRequest {
            return (
                InterceptorHelper()
                    .interceptorHelper(
                        AssetsClient()
                            .clientInterceptor
                    )
                )
        }
    }
}
