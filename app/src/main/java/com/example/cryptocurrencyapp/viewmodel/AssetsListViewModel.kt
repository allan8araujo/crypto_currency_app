package com.example.cryptocurrencyapp.viewmodel

import android.view.View
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.api.request.IAssetsRequest
import com.example.apilibrary.repository.const.Constants.Companion.AMAZON_ICON
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.DetailsFragmentBinding
import com.example.cryptocurrencyapp.utils.ProgressBarListener
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

    fun insertAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.insertAsset(assetItem)
    }

    fun deleteAsset(assetItem: AssetsItem) = viewModelScope.launch {
        repository.deleteAsset(assetItem)
    }

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

    fun setButtonAction(
        asset: AssetsItem,
        listAssetsItems: List<AssetsItem>,
        binding: DetailsFragmentBinding,
    ) {
        var findItemOnList: Boolean?
        settingImageIcon(asset, binding)
        with(binding) {
            iconAssetIdTextView.text = asset.asset_id
            findItemOnList = listAssetsItems.any {
                it.asset_id == asset.asset_id
            }
            if (findItemOnList == true) {
                setRemove(asset)
            } else {
                setAdd(asset)
            }
            settingPricesAndVolum(asset, asset.price_usd)
        }
    }

    private fun DetailsFragmentBinding.settingPricesAndVolum(
        asset: AssetsItem,
        price_usd: Double?,
    ) {
        if (asset.price_usd != null) {
            priceUsdTextView.text = price_usd.toString()
        } else {
            priceUsdTextView.setEms(5)
            priceUsdTextView.setCompoundDrawablesRelative(null, null, null, null)
            priceUsdTextView.text = "Indisponivel"
        }
        lastHourValueTextView.text = asset.volume_1hrs_usd.toString()
        lastWeekValueTextView.text = asset.volume_1day_usd.toString()
        lastMounthValueTextView.text = asset.volume_1mth_usd.toString()
    }

    private fun DetailsFragmentBinding.setAdd(
        asset: AssetsItem,
    ) {
        addButton.text = "Adicionar"
        starIconImageView.visibility = View.GONE
        addButton.setOnClickListener {
            insertAsset(asset)
        }
    }

    private fun DetailsFragmentBinding.setRemove(
        asset: AssetsItem,
    ) {
        addButton.text = "Remover"
        starIconImageView.visibility = View.VISIBLE
        addButton.setOnClickListener {
            deleteAsset(asset)
        }
    }

    private fun settingImageIcon(
        asset: AssetsItem,
        binding: DetailsFragmentBinding,
    ) {
        val progressBar = binding.detailsProgressBar
        progressBar.visibility = View.VISIBLE
        Glide.with(binding.root)
            .load(loadUrlFromGlide(asset))
            .placeholder(R.drawable.ic_coin_base)
            .listener(ProgressBarListener(progressBar))
            .centerCrop()
            .into(binding.coinIconImageView)
    }


}
