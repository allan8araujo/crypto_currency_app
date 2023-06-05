package com.example.cryptocurrencyapp

import android.app.Application
import com.example.cryptocurrencyapp.di.coinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoinApplication)
            modules(coinModule)
        }
    }
}
