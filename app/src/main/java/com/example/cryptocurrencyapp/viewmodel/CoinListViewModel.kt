package com.example.cryptocurrencyapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.apilibrary.repository.states.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CoinListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    private val _assetsLiveData: LiveData<DataResult<Assets>>
    val assetsLiveData: Flow<DataResult<Assets>>

    val allFavoriteAssets: Flow<List<AssetsItem>>

    init {
        val assetDao = AssetsDatabase.getDatabase(application).assetsDao()
        repository = Repository(assetDao)
        allFavoriteAssets = repository.getAllAssets()

        _assetsLiveData = getAllAssets()
        assetsLiveData = _assetsLiveData.asFlow()
    }

    fun getAllAssets() = repository.getApiAssets().asLiveData()

    fun insertAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.insertFavoriteAsset(assetItem)
    }

    fun deleteAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.deleteFavoriteAsset(assetItem)
    }

    fun filterType_(
        dataResults: DataResult<List<AssetsItem>>?,
        cryptoType: Any?,
    ): List<AssetsItem> {
        var newlist = listOf<AssetsItem>()
        when (dataResults) {
            is DataResult.Loading -> {
            }
            is DataResult.Success -> {
                newlist = dataResults.data.filter { assetItem ->
                    assetItem.type_is_crypto == cryptoType
                }
            }
            is DataResult.Error -> {
                newlist = dataResults.emptyDataResults
            }
            else -> {
            }
        }
        return newlist
    }
}
