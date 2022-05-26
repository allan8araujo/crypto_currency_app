package com.example.cryptocurrencyapp.data.api.retrofit

import com.example.cryptocurrencyapp.data.api.client.AssetsClient
import com.example.cryptocurrencyapp.data.api.client.SearchAssetsClient
import com.example.cryptocurrencyapp.data.api.interceptor.InterceptorHelper
import com.example.cryptocurrencyapp.factories.IconViewModelFactory
import com.example.cryptocurrencyapp.factories.ListViewModelFactory
import com.example.cryptocurrencyapp.factories.SearchedViewModelFactory

class RetrofitRequestHelper {
    companion object {
        fun getListAssets(): ListViewModelFactory {
            val listRepository =
                InterceptorHelper().interceptorHelper(AssetsClient().clientInterceptor)
            return ListViewModelFactory(listRepository)
        }

        fun getIcons(): IconViewModelFactory {
            val iconsRepository =
                InterceptorHelper().interceptorHelper(AssetsClient().clientInterceptor)
            return IconViewModelFactory(iconsRepository)
        }

        fun searchAssets(searchId: String): SearchedViewModelFactory {
            val searchRepository =
                InterceptorHelper().interceptorHelper(SearchAssetsClient(searchId).clientInterceptor)
            return SearchedViewModelFactory(searchRepository)
        }
    }
}
