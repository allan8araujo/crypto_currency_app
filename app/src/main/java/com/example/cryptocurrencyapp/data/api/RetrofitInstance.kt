package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.data.api.client.ListAssetsClient
import com.example.cryptocurrencyapp.data.api.client.SearchAssetsClient
import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.repository.AssetsRepository
import com.example.cryptocurrencyapp.factories.AssetsIconVMFactory
import com.example.cryptocurrencyapp.factories.AssetsListVMFactory
import com.example.cryptocurrencyapp.factories.AssetsSearchedVMFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun getAllAssets(): AssetsListVMFactory {
            return AssetsListVMFactory(settingInterceptor(ListAssetsClient.clientInterceptor))
        }

        fun getIcons(): AssetsIconVMFactory {
            return AssetsIconVMFactory(settingInterceptor(ListAssetsClient.clientInterceptor))
        }

        fun searchAssets(searchId: String): AssetsSearchedVMFactory {
            return AssetsSearchedVMFactory(settingInterceptor(SearchAssetsClient(searchId).clientInterceptor))
        }

        private fun settingInterceptor(interpertor: OkHttpClient): AssetsRepository {
            val retrofitInstance by lazy {
                Retrofit
                    .Builder()
                    .client(interpertor)
                    .baseUrl(Constants.PATH_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            val retrofitClient: RetrofitService by lazy {
                retrofitInstance.create(RetrofitService::class.java)
            }
            return AssetsRepository(retrofitClient)
        }
    }
}
