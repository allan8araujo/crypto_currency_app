package com.example.cryptocurrencyapp.viewmodel.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel

class ListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinListViewModel(application) as T
    }
}
