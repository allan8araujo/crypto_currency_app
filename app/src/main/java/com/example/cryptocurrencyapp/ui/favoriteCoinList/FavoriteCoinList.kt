package com.example.cryptocurrencyapp.ui.favoriteCoinList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.abstraction.AssetsItem
import com.example.abstraction.listMockedAssetsItems
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.commons.whiteBlackGradientColor
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.utils.toAssetsImage
import com.example.cryptocurrencyapp.utils.toMoneyFormat
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel

@Composable
fun FavoriteCoinList(
    navController: NavHostController,
    coinViewModel: CoinListViewModel,
    coinDetailSharedViewModel: CoinDetailSharedViewModel
) {
    val coinFavoriteList = coinViewModel.allFavoriteAssets.collectAsState(initial = null).value

    FavoriteCoinList(
        navController = navController,
        coinFavoriteList = coinFavoriteList,
        addCoin = { asset ->
            coinDetailSharedViewModel.addCoin(asset)
        }
    )
}

@OptIn(
    ExperimentalFoundationApi::class
)
@Composable
fun FavoriteCoinList(
    navController: NavHostController,
    coinFavoriteList: List<AssetsItem>?,
    addCoin: (AssetsItem) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        if (coinFavoriteList != null) items(coinFavoriteList) { asset ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
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
                asset.apply {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val iconUrl = asset.id_icon?.toAssetsImage()
                        if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                            modifier = Modifier
                                .weight(0.4f)
                                .aspectRatio(1f),
                            model = iconUrl,
                            contentDescription = "essa é a moeda $name",
                            loading = {
                                CircularProgressIndicator()
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
                            Text(text = price_usd?.toMoneyFormat().toString())
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteCoinList() {
    val navController = rememberNavController()
    val coinFavoriteList = listMockedAssetsItems

    FavoriteCoinList(
        navController = navController,
        coinFavoriteList = coinFavoriteList,
        addCoin = {}
    )
}