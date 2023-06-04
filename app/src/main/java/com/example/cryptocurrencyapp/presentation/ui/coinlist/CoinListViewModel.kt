package com.example.cryptocurrencyapp.presentation.ui.coinlist

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.retrofit.CoinApiInstance
import com.example.apilibrary.repository.api.retrofit.RetrofitService
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.apilibrary.repository.util.DataResult
import com.example.cryptocurrencyapp.commons.utils.FilterEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CoinListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    private val _assetsLiveData: LiveData<DataResult<List<AssetsItem>>>
    val assetsLiveData: Flow<DataResult<List<AssetsItem>>>

    val allFavoriteAssets: Flow<List<AssetsItem>>

    var filterType: MutableState<FilterEnum> = mutableStateOf(FilterEnum.allCurrencies)

    init {
        val assetDao = AssetsDatabase.getDatabase(application).assetsDao()
        val coinApiInstanceService =
            CoinApiInstance.getCryptoRetrofit().create(RetrofitService::class.java)

        repository = Repository(assetDao, coinApiInstanceService)
        allFavoriteAssets = repository.getFavoriteAssetsFromDatabase()

        _assetsLiveData = getAllAssets()
        assetsLiveData = _assetsLiveData.asFlow()
    }

    fun getAllAssets() = repository.getApiAssets().asLiveData()

    fun insertAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.insertFavoriteAssetToDatabase(assetItem)
    }

    fun deleteAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.deleteFavoriteAssetFromDatabase(assetItem)
    }

    fun filterByType(stateCoin: MutableState<CoinListState?>) {
        viewModelScope.launch {
            assetsLiveData.collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        stateCoin.value = when (filterType.value.type) {
                            FilterEnum.TRADITIONAL_CURRENCIES -> {
                                (CoinListState(isSucess = result.data.filter { assetItem ->
                                    assetItem.type_is_crypto == 0
                                }))
                            }

                            FilterEnum.CRYPTO_CURRENCIES -> {
                                (CoinListState(isSucess = result.data.filter { assetItem ->
                                    assetItem.type_is_crypto == 1
                                }))
                            }

                            FilterEnum.ALL_CURRENCIES -> {
                                (CoinListState(isSucess = result.data))
                            }

                            else -> {
                                null
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun filterByName(
        stateCoin: MutableState<CoinListState?>,
        filterText: String
    ) {
        viewModelScope.launch {
            assetsLiveData.collect { result ->
                when (result) {
                    is DataResult.Success -> {
                        stateCoin.value = (CoinListState(isSucess = result.data.filter {
                            it.name.contains(
                                other = filterText,
                                ignoreCase = true
                            ) || it.asset_id.contains(
                                other = filterText,
                                ignoreCase = true
                            )
                        }))
                    }

                    else -> {}
                }
            }
        }
    }
}
