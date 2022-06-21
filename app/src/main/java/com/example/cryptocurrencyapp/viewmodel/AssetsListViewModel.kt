package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.const.Constants.Companion.AMAZON_ICON
import com.example.cryptocurrencyapp.viewmodel.results.DataResult
import com.example.abstraction.funEmptyAssets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AssetsListViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val liveList = MutableLiveData<DataResult<List<com.example.abstraction.AssetsItem>>>()
    val assets: LiveData<DataResult<List<com.example.abstraction.AssetsItem>>> = liveList

    val database = repository.getDatabaseAssets()
    private lateinit var recycledApiList: List<com.example.abstraction.AssetsItem>

    private val favoriteLiveList = MutableLiveData<DataResult<List<com.example.abstraction.AssetsItem>>>()
    val favoriteAssets: LiveData<DataResult<List<com.example.abstraction.AssetsItem>>> = favoriteLiveList

    private val assetsRespository: IAssetsRequest = repository.getApiAssets()

    fun getAllAssets() {
        viewModelScope.launch {
            liveList.value = DataResult.Loading()
            try {
                val assetsResponseFromApi = withContext(Dispatchers.IO) {
                    assetsRespository.getAssets()
                }
                recycledApiList = assetsResponseFromApi.toAssets()
                liveList.value = DataResult.Success(recycledApiList)
            } catch (httpException: HttpException) {
                val assetsFromApi =
                    DataResult.Error<List<com.example.abstraction.AssetsItem>>(httpException,
                        com.example.abstraction.funEmptyAssets())
                liveList.value = assetsFromApi
            } catch (throwable: Throwable) {
                liveList.value = DataResult.Loading()
            }
        }
    }

    fun getFavoriteAssets() {
        viewModelScope.launch {
            favoriteLiveList.value = DataResult.Loading()
            try {
                favoriteLiveList.value = DataResult.Success(filterFavorites(recycledApiList))
            } catch (e: Throwable) {
                TODO()
            }
        }
    }

    private fun filterFavorites(assetsFromApi: List<com.example.abstraction.AssetsItem>): ArrayList<com.example.abstraction.AssetsItem> {
        val list: ArrayList<com.example.abstraction.AssetsItem> = arrayListOf()
        database.getAll().forEach { dataId ->
            assetsFromApi.find { assetsItem -> assetsItem.asset_id == dataId }
                ?.let { list.add(it) }
        }
        return list
    }

    fun com.example.abstraction.Assets.toAssets(): List<com.example.abstraction.AssetsItem> {
        return map {
            com.example.abstraction.AssetsItem(
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

    fun toAssetsImage(idIcon: String?): String? {
        idIcon?.let {
            return stringToUrl(idIcon)
        }
        return null
    }

    fun stringToUrl(idIcon: String): String {
        return AMAZON_ICON + idIcon.replace("-", "") + ".png"
    }

    fun loadUrlFromGlide(assetItem: com.example.abstraction.AssetsItem): String? {
        return assetItem.id_icon
    }
}
