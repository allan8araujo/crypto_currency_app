package com.example.cryptocurrencyapp.models

import com.example.apilibrary.repository.api.AssetsRepository
import com.example.apilibrary.repository.api.client.AssetsClient
import com.example.apilibrary.repository.api.interceptor.InterceptorHelper
import com.example.cryptocurrencyapp.viewmodel.factories.ListViewModelFactory

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): ListViewModelFactory {
            val listRepository =
                InterceptorHelper().interceptorHelper(AssetsClient().clientInterceptor)
            return ListViewModelFactory(listRepository as AssetsRepository)
        }
    }
}
