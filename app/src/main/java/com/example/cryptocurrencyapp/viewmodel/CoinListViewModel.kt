package com.example.cryptocurrencyapp.viewmodel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.ui.coinList.CoinListState
import com.example.cryptocurrencyapp.utils.FilterEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CoinListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    private val _assetsLiveData: LiveData<DataResult<Assets>>
    val assetsLiveData: Flow<DataResult<Assets>>

    val allFavoriteAssets: Flow<List<AssetsItem>>

    var filterType: MutableState<FilterEnum> = mutableStateOf(FilterEnum.allCurrencies)

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

    fun filterByType(stateCoin: MutableState<CoinListState?>) {
        viewModelScope.launch {
            assetsLiveData.collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        stateCoin.value = (CoinListState(isSucess = result.data))

                        when (filterType.value.type) {
                            FilterEnum.TRADITIONAL_CURRENCIES -> {
                                stateCoin.value =
                                    (CoinListState(isSucess = result.data.filter { assetItem ->
                                        assetItem.type_is_crypto == 0
                                    }))
                            }

                            FilterEnum.CRYPTO_CURRENCIES -> {
                                stateCoin.value =
                                    (CoinListState(isSucess = result.data.filter { assetItem ->
                                        assetItem.type_is_crypto == 1
                                    }))
                            }

                            FilterEnum.ALL_CURRENCIES -> {
                                stateCoin.value = (CoinListState(isSucess = result.data))
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}
