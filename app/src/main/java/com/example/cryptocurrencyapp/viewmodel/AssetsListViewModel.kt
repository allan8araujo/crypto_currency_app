package com.example.cryptocurrencyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem
import com.example.cryptocurrencyapp.models.assets.Assets.funEmptyAssets
import com.example.cryptocurrencyapp.models.assets.AssetsImage.AssetsImage
import com.example.cryptocurrencyapp.models.repository.IAssetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception

class AssetsListViewModel(
    private val assetsRespository: IAssetsRepository,
) : ViewModel() {
    val liveList = MutableLiveData<DataResult<List<AssetsItem>>>()
    val assets: LiveData<DataResult<List<AssetsItem>>> = liveList

    private val iconAsset = MutableLiveData<AssetsImage>()
    val icon: LiveData<AssetsImage> = iconAsset

    fun getAllAssets() {
        viewModelScope.launch {
            getAssetsData()
        }
    }

    private suspend fun getAssetsIcons() {

        try {
            val allIcons = withContext(Dispatchers.IO) {
                assetsRespository.getIcons()
            }
            iconAsset.value = allIcons
        } catch (e: Throwable) {
        }
    }

    private suspend fun getAssetsData() {
        liveList.value = DataResult.Loading()
        try {
            val assetsFromApi = withContext(Dispatchers.IO) {
                assetsRespository.getAssets()
            }
            liveList.value = DataResult.Sucess(assetsFromApi)
        } catch (e: HttpException) {
            val assetsFromApi = DataResult.Error<List<AssetsItem>>(e, funEmptyAssets())
            liveList.value = assetsFromApi
        }
    }
}
