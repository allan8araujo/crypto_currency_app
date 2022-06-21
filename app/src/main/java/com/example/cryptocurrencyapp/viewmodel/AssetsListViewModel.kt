package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.*
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.const.Constants.Companion.AMAZON_ICON
import com.example.cryptocurrencyapp.viewmodel.results.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AssetsListViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val liveList = MutableLiveData<DataResult<List<AssetsItem>>>()
    val assets: LiveData<DataResult<List<AssetsItem>>> = liveList

    private lateinit var recycledApiList: List<AssetsItem>

    private val favoriteLiveList = MutableLiveData<List<AssetsItem>>()
    val favoriteAssets = repository.getAssets().asLiveData()

    private val assetsRespository: IAssetsRequest = repository.getApiAssets()

    fun insertAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.insertAsset(assetItem)
    }

    fun deleteAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.deleteAsset(assetItem)
    }

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
                    DataResult.Error<List<AssetsItem>>(
                        httpException,
                        com.example.abstraction.funEmptyAssets()
                    )
                liveList.value = assetsFromApi
            } catch (throwable: Throwable) {
                liveList.value = DataResult.Loading()
            }
        }
    }

    fun getFavoriteAssets() {
        viewModelScope.launch {
            try {
                favoriteLiveList.value = filterFavorites(recycledApiList)
            } catch (e: Throwable) {
                TODO()
            }
        }
    }

    private fun filterFavorites(assetsFromApi: List<AssetsItem>): ArrayList<AssetsItem> {
        val list: ArrayList<AssetsItem> = arrayListOf()
        favoriteAssets.value?.forEach { dataId ->
            assetsFromApi.find { assetsItem ->
                assetsItem.asset_id == dataId.asset_id
            }
                ?.let { list.add(it) }
        }
        return list
    }

    fun Assets.toAssets(): List<AssetsItem> {
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

    fun toAssetsImage(idIcon: String?): String? {
        idIcon?.let {
            return stringToUrl(idIcon)
        }
        return null
    }

    fun stringToUrl(idIcon: String): String {
        return AMAZON_ICON + idIcon.replace("-", "") + ".png"
    }

    fun loadUrlFromGlide(assetItem: AssetsItem): String? {
        return assetItem.id_icon
    }
}
