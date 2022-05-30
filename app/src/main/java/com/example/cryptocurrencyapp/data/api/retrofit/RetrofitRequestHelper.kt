package com.example.cryptocurrencyapp.data.api.retrofit

import com.example.cryptocurrencyapp.data.api.client.AssetsClient
import com.example.cryptocurrencyapp.data.api.interceptor.InterceptorHelper
import com.example.cryptocurrencyapp.factories.ListViewModelFactory

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): ListViewModelFactory {
            val listRepository =
                InterceptorHelper().interceptorHelper(AssetsClient().clientInterceptor)
            return ListViewModelFactory(listRepository)
        }
    }
}
