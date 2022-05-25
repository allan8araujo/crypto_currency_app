package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.data.models.Assets
import com.example.cryptocurrencyapp.data.repository.IAssetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchedAssetsViewModel(
    private val assetsRespository: IAssetsRepository
) : ViewModel() {

    private val searchedAsset = MutableLiveData<Assets>()
    val asset: LiveData<Assets> = searchedAsset

    fun searchedAsset() {
        viewModelScope.launch {
            val assetSearched = withContext(Dispatchers.IO) {
                assetsRespository.searchAsset()
            }
            searchedAsset.value = assetSearched
        }
    }
}
