package com.example.cryptocurrencyapp.models.repository.api.retrofit

import com.example.cryptocurrencyapp.models.repository.api.client.AssetsClient
import com.example.cryptocurrencyapp.models.repository.api.interceptor.InterceptorHelper
import com.example.cryptocurrencyapp.viewmodel.factories.ListViewModelFactory

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): ListViewModelFactory {
            val listRepository =
                InterceptorHelper().interceptorHelper(AssetsClient().clientInterceptor)
            return ListViewModelFactory(listRepository)
        }
    }
}
