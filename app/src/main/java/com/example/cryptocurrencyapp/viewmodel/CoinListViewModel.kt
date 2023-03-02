package com.example.cryptocurrencyapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.utils.toAssetsImage
import com.example.cryptocurrencyapp.view.adapters.CoinListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CoinListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    private val _assetsLiveData: LiveData<DataResult<Assets>>
    val assetsLiveData: Flow<DataResult<Assets>>

    val allFavoriteAssets: LiveData<List<AssetsItem>>

    init {
        val assetDao = AssetsDatabase.getDatabase(application).assetsDao()
        repository = Repository(assetDao)
        allFavoriteAssets = repository.getAllAssets

        _assetsLiveData = getAllAssets()
        assetsLiveData = _assetsLiveData.asFlow()
    }

    fun getAllAssets() = repository.getApiAssets().asLiveData()

    private fun Assets.toAssets(): List<AssetsItem> {
        return map {
            AssetsItem(
                asset_id = it.asset_id,
                name = it.name,
                type_is_crypto = it.type_is_crypto,
                data_quote_start = it.data_quote_start,
                data_quote_end = it.data_quote_end,
                data_orderbook_start = it.data_orderbook_start,
                data_orderbook_end = it.data_orderbook_end,
                data_trade_start = it.data_trade_start,
                data_trade_end = it.data_trade_end,
                data_symbols_count = it.data_symbols_count,
                volume_1hrs_usd = it.volume_1hrs_usd,
                volume_1day_usd = it.volume_1day_usd,
                volume_1mth_usd = it.volume_1mth_usd,
                price_usd = it.price_usd,
                id_icon = toAssetsImage(it.id_icon),
                data_start = it.data_start,
                data_end = it.data_end
            )
        }
    }


    fun insertAsset(assetItem: AssetsItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFavoriteAsset(assetItem)
        Log.i("favoriteAssets", "insertAsset: ${repository.getAllAssets.value}")
    }

    fun deleteAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.deleteFavoriteAsset(assetItem)
    }

    fun searchInList(
        searchValue: String?,
        dataResults: DataResult<List<AssetsItem>>?,
        listAdapter: CoinListAdapter,
    ) {
        var listResults = listOf<AssetsItem>()
        val searchValueUpperCase = searchValue?.uppercase()
        when (dataResults) {
            is DataResult.Loading -> {
            }
            is DataResult.Success -> {
                listResults = dataResults.data.filter { assetItem ->
                    (assetItem.asset_id.uppercase() in searchValueUpperCase!!) || (assetItem.name.uppercase() in searchValueUpperCase)
                }
            }
            is DataResult.Error -> {
                listResults = dataResults.emptyDataResults
            }
            else -> {}
        }
        listAdapter.submitList(listResults)
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
