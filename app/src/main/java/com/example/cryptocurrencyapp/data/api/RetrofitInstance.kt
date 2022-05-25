package com.example.cryptocurrencyapp.data.api

import com.example.cryptocurrencyapp.viewmodel.AssetsViewModelFactory
import com.example.cryptocurrencyapp.data.api.client.ListAssetsClient
import com.example.cryptocurrencyapp.data.api.client.SearchAssetsClient
import com.example.cryptocurrencyapp.data.const.Constants
import com.example.cryptocurrencyapp.data.repository.AssetsRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun getAllAssets(): AssetsViewModelFactory {
            return settingInterceptor(ListAssetsClient.clientInterceptor)
        }

        fun searchAssets(searchId: String): AssetsViewModelFactory {
            return settingInterceptor(SearchAssetsClient(searchId).clientInterceptor)
        }

        private fun settingInterceptor(interpertor: OkHttpClient): AssetsViewModelFactory {
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
            val assetsRespository = AssetsRepository(retrofitClient)

            return AssetsViewModelFactory(assetsRespository)
        }
    }
}
