package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.data.models.Assets
import com.example.cryptocurrencyapp.data.models.AssetsItem
import com.example.cryptocurrencyapp.data.repository.IAssetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AssetsListViewModel(
    private val assetsRespository: IAssetsRepository
) : ViewModel() {
    private val liveList = MutableLiveData<List<AssetsItem>>()
    val assets: LiveData<List<AssetsItem>> = liveList

    fun getAllAssets() {
        viewModelScope.launch {
            val assetsFromApi = withContext(Dispatchers.IO) {
                assetsRespository.getAssets()
            }
            liveList.value = assetsFromApi
        }
    }
}