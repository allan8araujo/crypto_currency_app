package com.example.cryptocurrencyapp.ui.coinList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.abstraction.listMockedAssetsItems
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.utils.*
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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


    CoinList(
        navController = navController,
        favoriteAssets = favoriteAssets,
        filterTypeState = filterTypeState,
        assetsLiveData = assetsLiveData,
        filterByType = coinViewModel?.filterByType(stateCoin),
        stateCoin = stateCoin,
        addCoin = { newSelectedCoin ->
            coinDetailSharedViewModel.addCoin(newSelectedCoin)
        },
        setIsFavorite = { isFavoriteCoin ->
            coinDetailSharedViewModel.setIsFavorite(isFavoriteCoin)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinList(
    navController: NavHostController,
    favoriteAssets: State<List<AssetsItem>?>?,
    filterTypeState: FilterEnum?,
    assetsLiveData: Flow<DataResult<Assets>>?,
    filterByType: Unit?,
    stateCoin: MutableState<CoinListState?>,
    addCoin: (AssetsItem) -> Unit,
    setIsFavorite: (Boolean) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val stateCoinList = stateCoin.value
    val notNullList = stateCoinList?.isSucess != null
    LaunchedEffect(key1 = filterTypeState) {
        Log.i("filterType", "filterType:${filterTypeState?.type}")
        scope.launch {
            filterByType
        }
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
            .padding(32.dp), color = Color.White
    )

    Column(modifier = Modifier.fillMaxSize()) {

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val currentDate = dateFormat.format(Date())

        Text(modifier = Modifier.fillMaxWidth(), text = currentDate, textAlign = TextAlign.Center)

        LazyColumn {
            item {
                LazyRow {
                    if (notNullList) {

                        val removeNullPrices = stateCoinList?.isSucess!!.filter { assetItem_ ->
                            assetItem_.price_usd != null
                        }

                        val orderedList = removeNullPrices.sortedByDescending { assetItem_ ->
                            assetItem_.volume_1mth_usd
                        }

                        items(orderedList.take(20)) { asset ->
                            Card(modifier = Modifier.padding(8.dp), onClick = {
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
                val isFavorite = favoriteAssets?.value?.any { it.name == asset.name } == true

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

@Preview
@Composable
private fun assetItemPreview() {
    AssetItem(asset = listMockedAssetsItems[1], isFavorite = false)
}

@Composable
private fun AssetItem(asset: AssetsItem, isFavorite: Boolean = false) {
    asset.apply {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            val iconUrl = id_icon?.toAssetsImage()
            if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                modifier = Modifier
                    .weight(0.4f)
                    .aspectRatio(1f),
                model = iconUrl,
                contentDescription = "essa é a moeda $name",
                loading = {
                    CircularProgressIndicator(
                        color = Color(greenColor)
                    )
                },
            )
            else AsyncImage(
                modifier = Modifier
                    .weight(0.4f)
                    .aspectRatio(1f),
                model = R.drawable.ic_coin_base,
                contentDescription = "essa é a moeda $name",
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = name)
                Text(text = asset_id)
            }

            val textValue = formatDisplayedText()

            Text(
                text = textValue,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )

            if (isFavorite) {
                Image(
                    modifier = Modifier
                        .weight(0.2f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.ic_baseline_star_coin_24),
                    contentDescription = "essa é a moeda $name",
                )
            } else {
                Image(
                    modifier = Modifier
                        .weight(0.2f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.baseline_star_outline_24),
                    contentDescription = "essa é a moeda $name",
                )
            }
        }
    }
}

@Composable
private fun AssetItemHorizontal(asset: AssetsItem) {
    asset.apply {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val iconUrl = id_icon?.toAssetsImage()
            if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                model = iconUrl,
                contentDescription = "essa é a moeda $name",
                loading = {
                    CircularProgressIndicator(
                        color = Color(greenColor)
                    )
                },
            )
            else AsyncImage(
                model = R.drawable.ic_coin_base,
                contentDescription = "essa é a moeda $name",
            )
            Column {
                Text(text = name)
                Text(text = asset_id)
            }

            val textValue = formatDisplayedText()

            Text(
                text = textValue,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun CoinListPreview() {
    val coinDetailSharedViewModel = CoinDetailSharedViewModel()
    val navController = rememberNavController()

    CoinList(
        coinDetailSharedViewModel = coinDetailSharedViewModel, navController = navController
    )
}