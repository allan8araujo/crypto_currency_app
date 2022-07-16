package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.*
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.cryptocurrencyapp.helper.UrlHelper
import com.example.cryptocurrencyapp.viewmodel.states.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AssetsListViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val assetsLiveData = MutableLiveData<DataResult<List<AssetsItem>>>() //
    val assetsFromResultApi: LiveData<DataResult<List<AssetsItem>>> = assetsLiveData
    private val assetsResultApi: IAssetsRequest = repository.getApiAssets()
    private lateinit var assetsResponseFromApiWithIcon: List<AssetsItem>

    val allFavoriteAssets: LiveData<List<AssetsItem>> = repository.getAllAssets.asLiveData()

    fun getAllAssets() {
        viewModelScope.launch {
            assetsLiveData.value = DataResult.Loading()
            try {
                val assetsResponseFromApi = withContext(Dispatchers.IO) {
                    assetsResultApi.getAssets()
                }
                assetsResponseFromApiWithIcon = assetsResponseFromApi.toAssets()
                assetsLiveData.value = DataResult.Success(assetsResponseFromApiWithIcon)
            } catch (httpException: HttpException) {
                val assetsFromApi =
                    DataResult.Error<List<AssetsItem>>(
                        httpException,
                        com.example.abstraction.funEmptyAssets()
                    )
                assetsLiveData.value = assetsFromApi
            } catch (throwable: Throwable) {
                assetsLiveData.value = DataResult.Loading()
            }
        }
    }

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

    private fun toAssetsImage(idIcon: String?): String? {
        idIcon?.let {
            return UrlHelper().stringToUrl(idIcon)
        }
        return null
    }

    fun insertAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.insertAsset(assetItem)
    }

    fun deleteAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.deleteAsset(assetItem)
    }
}
