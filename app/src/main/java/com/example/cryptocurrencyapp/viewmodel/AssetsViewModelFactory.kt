package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.data.repository.AssetsRepository

class AssetsViewModelFactory(private val repository: AssetsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AssetsListViewModel(repository) as T
    }
}