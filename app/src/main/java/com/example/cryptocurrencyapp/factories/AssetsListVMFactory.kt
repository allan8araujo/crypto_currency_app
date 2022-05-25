package com.example.cryptocurrencyapp.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.data.repository.AssetsRepository
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class AssetsListVMFactory(private val repository: AssetsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AssetsListViewModel(repository) as T
    }
}
