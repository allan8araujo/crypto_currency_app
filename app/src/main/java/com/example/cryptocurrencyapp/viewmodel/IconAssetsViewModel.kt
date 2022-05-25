package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.data.models.AssetsImage.AssetsImage
import com.example.cryptocurrencyapp.data.repository.IAssetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IconAssetsViewModel(
    private val assetsRespository: IAssetsRepository
) : ViewModel() {

    private val iconAsset = MutableLiveData<AssetsImage>()
    val icon: LiveData<AssetsImage> = iconAsset

    fun IconsAssets() {
        viewModelScope.launch {
            val allIcons = withContext(Dispatchers.IO) {
                assetsRespository.getIcons()
            }
            iconAsset.value = allIcons
        }
    }
}
