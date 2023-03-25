package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.abstraction.listMockedAssetsItems
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.utils.*
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun CoinList(
    coinViewModel: CoinListViewModel? = null,
    coinDetailSharedViewModel: CoinDetailSharedViewModel,
    navController: NavHostController
) {
    val favoriteAssets = coinViewModel?.allFavoriteAssets?.collectAsState(initial = null)
    val filterTypeState = coinViewModel?.filterType?.value
    val assetsLiveData = coinViewModel?.assetsLiveData
    val stateCoin = remember { mutableStateOf<CoinListState?>(null) }
    val currentWidthSize = (LocalView.current.width / 8f).dp
    val currentHeightSize = (LocalView.current.height / 8f).dp

    CoinList(
        navController = navController,
        favoriteAssets = favoriteAssets?.value,
        filterTypeState = filterTypeState,
        assetsLiveData = assetsLiveData,
        filterByType = coinViewModel?.filterByType(stateCoin),
        stateCoin = stateCoin,
        addCoin = { newSelectedCoin ->
            coinDetailSharedViewModel.addCoin(newSelectedCoin)
        },
        setIsFavorite = { isFavoriteCoin ->
            coinDetailSharedViewModel.setIsFavorite(isFavoriteCoin)
        },
        currentWidthSize = currentWidthSize,
        currentHeightSize = currentHeightSize
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinList(
    navController: NavHostController,
    favoriteAssets: List<AssetsItem>?,
    filterTypeState: FilterEnum?,
    assetsLiveData: Flow<DataResult<Assets>>?,
    filterByType: Unit?,
    stateCoin: MutableState<CoinListState?>,
    addCoin: (AssetsItem) -> Unit,
    setIsFavorite: (Boolean) -> Unit,
    currentWidthSize: Dp,
    currentHeightSize: Dp,
) {
    val scope = rememberCoroutineScope()
    val stateCoinList = stateCoin.value
    val notNullList = stateCoinList?.isSucess != null

    LaunchedEffect(key1 = filterTypeState) {
        filterByType
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            assetsLiveData?.collect { result ->
                when (result) {
                    is DataResult.Loading -> stateCoin.value = (CoinListState(isLoading = true))
                    is DataResult.Success -> stateCoin.value =
                        (CoinListState(isSucess = result.data))
                    is DataResult.Error -> stateCoin.value =
                        (CoinListState(isError = result.throwable.message))
                    else -> {}
                }
            }
        }
    }

    if (stateCoin.value?.isLoading == true) CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp), color = Color.White
    )

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn {
            item {
                LazyRow() {
                    if (notNullList) {

                        val removeNullPrices = stateCoinList?.isSucess!!.filter { assetItem_ ->
                            assetItem_.price_usd != null
                        }

                        val orderedList = removeNullPrices.sortedByDescending { assetItem_ ->
                            assetItem_.volume_1mth_usd
                        }

                        items(orderedList.take(20)) { asset ->
                            Card(modifier = Modifier
                                .padding(8.dp)
                                .width(currentWidthSize)
                                .height(currentHeightSize),
                                onClick = {
                                    addCoin(asset)
                                    navController.navigate(NavigationScreens.CoinDetailScreen.route)
                                }) {
                                AssetItemHorizontal(asset)
                            }
                        }
                    }
                }
            }

            if (notNullList) items(stateCoinList?.isSucess!!) { asset ->
                val isFavorite = favoriteAssets?.any { it.name == asset.name } == true

                Card(modifier = Modifier.padding(8.dp), onClick = {

                    setIsFavorite(isFavorite)

                    addCoin(asset)
                    navController.navigate(NavigationScreens.CoinDetailScreen.route)
                }) {
                    AssetItem(asset, isFavorite)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListPreview() {
    val navController = rememberNavController()

    val favoriteAssets = listMockedAssetsItems.toList()

    val filterTypeState = remember { mutableStateOf<FilterEnum?>(null) }
    val assetsLiveData = remember { mutableStateOf<Flow<DataResult<Assets>>?>(null) }
    val filterByType = null
    val stateCoin =
        remember { mutableStateOf<CoinListState?>(CoinListState(isSucess = listMockedAssetsItems)) }
    val selectedCoin = remember { mutableStateOf<AssetsItem?>(null) }
    val setIsFavorite = { isFavoriteCoin: Boolean ->
    }

    CoinList(
        navController = navController,
        favoriteAssets = favoriteAssets,
        filterTypeState = filterTypeState.value,
        assetsLiveData = assetsLiveData.value,
        filterByType = filterByType,
        stateCoin = stateCoin,
        addCoin = { newSelectedCoin ->
            selectedCoin.value = newSelectedCoin
        },
        setIsFavorite = setIsFavorite,
        currentWidthSize = 130.dp,
        currentHeightSize = 130.dp,
    )
}