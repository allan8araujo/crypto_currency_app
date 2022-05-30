package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.data.models.AssetsImage.AssetsImage
import com.example.cryptocurrencyapp.data.repository.IAssetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AssetsListViewModel(
    private val assetsRespository: IAssetsRepository,
) : ViewModel() {
    private val liveList = MutableLiveData<List<AssetsItem>>()
    private val iconAsset = MutableLiveData<AssetsImage>()
    val assets: LiveData<List<AssetsItem>> = liveList
    val icon: LiveData<AssetsImage> = iconAsset

    fun getAllAssets() {
        viewModelScope.launch {
            getAssetsIcons()
            getAssetsData()
        }
    }

    private suspend fun getAssetsIcons() {
        val allIcons = withContext(Dispatchers.IO) {
            assetsRespository.getIcons()
        }
        iconAsset.value = allIcons
    }

    private suspend fun getAssetsData() {
        val assetsFromApi = withContext(Dispatchers.IO) {
            assetsRespository.getAssets()
        }

        liveList.value = assetsFromApi
    }
}
