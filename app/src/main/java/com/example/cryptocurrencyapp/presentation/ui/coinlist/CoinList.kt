package com.example.cryptocurrencyapp.presentation.ui.coinlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.abstraction.Assets
import com.example.abstraction.AssetsItem
import com.example.abstraction.toAssetsItem
import com.example.apilibrary.repository.util.DataResult
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.assets.listMockedAssetsItems
import com.example.cryptocurrencyapp.commons.ui.blackWhiteGradientColor
import com.example.cryptocurrencyapp.commons.ui.poppinsRegular
import com.example.cryptocurrencyapp.commons.ui.whiteBlackGradientColor
import com.example.cryptocurrencyapp.commons.utils.FilterEnum
import com.example.cryptocurrencyapp.commons.utils.iceWhiteColor
import com.example.cryptocurrencyapp.presentation.ui.NavigationScreens
import com.example.cryptocurrencyapp.presentation.ui.coindetail.CoinDetailSharedViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

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
    val currentHeightSize = (LocalView.current.height / 16f).dp
    val filterText = remember { mutableStateOf("") }

    CoinList(
        navController = navController,
        favoriteAssets = favoriteAssets?.value,
        filterTypeState = filterTypeState,
        assetsLiveData = assetsLiveData,
        filterByType = { stateCoin_ ->
            coinViewModel?.filterByType(stateCoin_)
        },
        filterByName = coinViewModel?.filterByName(stateCoin, filterText.value),
        filterText = filterText,
        stateCoin = stateCoin,
        addCoin = { newSelectedCoin ->
            coinDetailSharedViewModel.addCoin(newSelectedCoin)
        },
        setIsFavorite = { isFavoriteCoin ->
            coinDetailSharedViewModel.setIsFavorite(isFavoriteCoin)
        },
        currentWidthSize = currentWidthSize,
        currentHeightSize = currentHeightSize,
        whiteBlackGradientColor = whiteBlackGradientColor,
        blackWhiteGradientColor = blackWhiteGradientColor
    )
}

@Composable
fun CoinList(
    navController: NavHostController,
    favoriteAssets: List<AssetsItem>?,
    filterTypeState: FilterEnum?,
    assetsLiveData: Flow<DataResult<List<AssetsItem>>>?,
    filterByType: (MutableState<CoinListState?>) -> Unit,
    filterByName: Unit?,
    filterText: MutableState<String>,
    stateCoin: MutableState<CoinListState?>,
    addCoin: (AssetsItem) -> Unit,
    setIsFavorite: (Boolean) -> Unit,
    currentWidthSize: Dp,
    currentHeightSize: Dp,
    whiteBlackGradientColor: Brush,
    blackWhiteGradientColor: Brush,
) {
    val scope = rememberCoroutineScope()
    val stateCoinList = stateCoin.value
    val notNullList = stateCoinList?.isSucess != null

    LaunchedEffect(key1 = filterTypeState) {
        filterByType(stateCoin)
    }

    LaunchedEffect(key1 = filterByName) {
        filterByName
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val titleTextState = remember { "Top Coins" }

        LazyColumn {
            item {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = titleTextState,
                    textAlign = TextAlign.Start,
                    fontFamily = poppinsRegular,
                    fontSize = 18.sp,
                    color = Color(iceWhiteColor)
                )
            }
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
                            Box(modifier = Modifier
                                .padding(end = 16.dp)
                                .width(currentWidthSize)
                                .height(currentHeightSize)
                                .clip(RoundedCornerShape(16))
                                .border(
                                    width = 1.dp,
                                    brush = whiteBlackGradientColor,
                                    shape = RoundedCornerShape(16)
                                )
                                .background(
                                    brush = whiteBlackGradientColor,
                                )
                                .clickable {
                                    addCoin(asset)
                                    navController.navigate(NavigationScreens.CoinDetailScreen.route)
                                }) {
                                AssetItemHorizontal(asset)
                            }
                        }
                    }
                }
            }

            item {
                if (stateCoin.value?.isLoading != true)
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .clip(RoundedCornerShape(16))
                            .border(
                                width = 1.dp,
                                brush = whiteBlackGradientColor,
                                shape = RoundedCornerShape(16)
                            )
                            .background(
                                color = Color(0xFF0F0F0F),
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        leadingIcon = {
                            Icon(
                                painterResource(R.drawable.ic_baseline_search_24),
                                contentDescription = null
                            )
                        },
                        value = filterText.value,
                        onValueChange = {
                            filterText.value = it
                        },
                        maxLines = 1,
                        textStyle = TextStyle(textAlign = TextAlign.Start)
                    )
            }

            item { Spacer(modifier = Modifier.padding(8.dp)) }
            if (notNullList) items(stateCoinList?.isSucess!!) { asset ->
                val isFavorite = favoriteAssets?.any { it.name == asset.name } == true

                Box(modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16))
                    .background(brush = blackWhiteGradientColor)
                    .clickable {
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

    val favoriteAssets = listMockedAssetsItems.toList().map { it.toAssetsItem() }

    val filterTypeState = remember { mutableStateOf<FilterEnum?>(null) }
    val assetsLiveData = remember { mutableStateOf<Flow<DataResult<Assets>>?>(null) }
    val filterText = remember { mutableStateOf("") }
    val stateCoin =
        remember { mutableStateOf<CoinListState?>(CoinListState(isSucess = favoriteAssets)) }
    val selectedCoin = remember { mutableStateOf<AssetsItem?>(null) }
    val setIsFavorite = { isFavoriteCoin: Boolean ->
    }

    CoinList(
        navController = navController,
        favoriteAssets = favoriteAssets,
        filterTypeState = filterTypeState.value,
        assetsLiveData = assetsLiveData.value,
        filterByType = { stateCoin ->

        },
        filterByName = null,
        filterText = filterText,
        stateCoin = stateCoin,
        addCoin = { newSelectedCoin ->
            selectedCoin.value = newSelectedCoin
        },
        setIsFavorite = setIsFavorite,
        currentWidthSize = 130.dp,
        currentHeightSize = 130.dp,
        whiteBlackGradientColor = whiteBlackGradientColor,
        blackWhiteGradientColor = blackWhiteGradientColor,
    )
}
