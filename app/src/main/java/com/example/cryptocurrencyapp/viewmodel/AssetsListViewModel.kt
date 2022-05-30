package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.data.models.Assets.funEmptyAssets
import com.example.cryptocurrencyapp.data.models.AssetsImage.AssetsImage
import com.example.cryptocurrencyapp.data.repository.IAssetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AssetsListViewModel(
    private val assetsRespository: IAssetsRepository,
) : ViewModel() {
    private val liveList = MutableLiveData<DataResult<List<AssetsItem>>>()
    val assets: LiveData<DataResult<List<AssetsItem>>> = liveList

    private val iconAsset = MutableLiveData<AssetsImage>()
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
        liveList.value = DataResult.Loading()
        try {
            val assetsFromApi = withContext(Dispatchers.IO) {
                assetsRespository.getAssets()
            }
            liveList.value = DataResult.Sucess(assetsFromApi)
        } catch (e: Throwable) {
            val assetsFromApi = DataResult.Error<List<AssetsItem>>(e, funEmptyAssets())
            liveList.value = assetsFromApi
        }
    }
}
