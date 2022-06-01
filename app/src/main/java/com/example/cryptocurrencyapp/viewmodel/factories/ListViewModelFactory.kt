package com.example.cryptocurrencyapp.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apilibrary.repository.api.AssetsRepository
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class ListViewModelFactory(private val repository: AssetsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AssetsListViewModel(repository) as T
    }
}
