package com.example.cryptocurrencyapp.di

import android.app.Application
import com.example.apilibrary.repository.RepositoryImpl
import com.example.apilibrary.repository.api.interceptor.InterceptorList
import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.const.Constants
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.cryptocurrencyapp.presentation.ui.coinlist.CoinListViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coinModule = module {
    single {
        Retrofit
            .Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(InterceptorList())
                    .build()
            )
            .baseUrl(Constants.PATH_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    single {
        androidContext().let { context ->
            AssetsDatabase.getDatabase(context).assetsDao()
        }
    }

    viewModel {
        CoinListViewModel(
            RepositoryImpl(get(), get()),
            androidContext().applicationContext as Application
        )
    }
}
