package com.example.cryptocurrencyapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.request.AssetsRequest
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.const.Constants.Companion.AMAZON_ICON
import com.example.apilibrary.repository.response.AssetsDTO.AssetsDTO
import com.example.apilibrary.repository.roomDataBase.AssetsDAO.AssetsItemDAO
import com.example.apilibrary.repository.roomDataBase.request.AssetsRequestDao
import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem
import com.example.cryptocurrencyapp.models.assets.Assets.funEmptyAssets
import com.example.cryptocurrencyapp.models.assets.AssetsImage.AssetsImage
import com.example.cryptocurrencyapp.viewmodel.results.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AssetsListViewModel(
    private val assetsRespository: IAssetsRequest,
) : ViewModel() {
    private val liveList = MutableLiveData<DataResult<List<AssetsItem>>>()
    val assets: LiveData<DataResult<List<AssetsItem>>> = liveList

    private val iconAsset = MutableLiveData<AssetsImage>()
    val icon: LiveData<AssetsImage> = iconAsset

    //room variables
    private lateinit var roomRequest: Repository


    fun getAllAssets() {
        viewModelScope.launch {
            getAssetsData()
        }
    }

    fun addFavoriteAsset(context: Context, asset: AssetsItem) {
        val dao = Repository().getDatabase(context)
        val repo: AssetsRequestDao = AssetsRequestDao(dao)

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repo.addAsset(toAssetItemDao(asset))
                }
            } catch (e: Exception) {
                Toast.makeText(context, "não deu para adicionar" + e.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        }


    }


    fun getAllDatabaseAssets(context: Context): ArrayList<AssetsItem?> {
        val dao = Repository().getDatabase(context)
        var list: List<AssetsItemDAO>? = listOf()

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    list = AssetsRequestDao(dao).readAllData.value
                }
            } catch (e: Exception) {
                list = arrayListOf()
            }
        }
        return if (!list.isNullOrEmpty()) {
            toAssetItemList(list!!)
        } else {
            arrayListOf()
        }
    }

    private suspend fun getAssetsData() {
        liveList.value = DataResult.Loading()
        try {
            val assetsResponseFromApi = withContext(Dispatchers.IO) {
                assetsRespository.getAssets()
            }
            val assetsFromApi = assetsResponseFromApi.toAssets()
            liveList.value = DataResult.Sucess(assetsFromApi)
        } catch (e: Throwable) {
            val assetsFromApi = DataResult.Error<List<AssetsItem>>(e, funEmptyAssets())
            liveList.value = assetsFromApi
        }
    }

    private fun toAssetItemDao(asset: AssetsItem): AssetsItemDAO {
        return AssetsItemDAO(
            asset_id = asset.asset_id,
            asset_name = asset.name,
            price_usd = asset.price_usd,
            type_is_crypto = asset.type_is_crypto,
            volume_1day_usd = asset.volume_1day_usd,
            volume_1hrs_usd = asset.volume_1hrs_usd,
            volume_1mth_usd = asset.volume_1mth_usd,
            id_icon = asset.id_icon
        )
    }

    private fun toAssetItemList(list: List<AssetsItemDAO>): ArrayList<AssetsItem?> {
        var convertedList: ArrayList<AssetsItem?> = ArrayList()
        list.forEach {
            convertedList.add(
                AssetsItem(
                    asset_id = it.asset_id,
                    name = it.asset_name,
                    type_is_crypto = it.type_is_crypto,
                    data_quote_start = null,
                    data_quote_end = null,
                    data_orderbook_start = null,
                    data_orderbook_end = null,
                    data_trade_start = null,
                    data_trade_end = null,
                    data_symbols_count = 0,
                    volume_1hrs_usd = it.volume_1hrs_usd,
                    volume_1day_usd = it.volume_1day_usd,
                    volume_1mth_usd = it.volume_1mth_usd,
                    price_usd = it.price_usd,
                    id_icon = toAssetsImage(it.id_icon),
                    data_start = null,
                    data_end = null
                )
            )
        }
        return convertedList
    }


    private fun AssetsDTO.toAssets(): List<AssetsItem> {
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
            return stringToUrl(idIcon)
        }
        return null
    }

    private fun stringToUrl(idIcon: String): String {
        return AMAZON_ICON + idIcon.replace("-", "") + ".png"
    }

    fun loadUrlFromGlide(assetItem: AssetsItem): String? {
        return assetItem.id_icon
    }
}
