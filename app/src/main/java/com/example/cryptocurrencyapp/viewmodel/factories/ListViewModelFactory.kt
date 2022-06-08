package com.example.cryptocurrencyapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class ListViewModelFactory(private val request: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AssetsListViewModel(request) as T
    }
}
