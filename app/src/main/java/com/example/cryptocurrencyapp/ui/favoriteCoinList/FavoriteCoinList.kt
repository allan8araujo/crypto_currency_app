package com.example.cryptocurrencyapp.ui.favoriteCoinList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.utils.toAssetsImage
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun FavoriteCoinList(
    navController: NavHostController,
    coinViewModel: CoinListViewModel,
    coinDetailSharedViewModel: CoinDetailSharedViewModel
) {
    val coinFavoriteList = coinViewModel.allFavoriteAssets.collectAsState(initial = null).value
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        if (coinFavoriteList != null) items(coinFavoriteList) { asset ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), onClick = {
                coinDetailSharedViewModel.addCoin(asset)
                navController.navigate(NavigationScreens.CoinDetailScreen.route)
            }) {
                asset.apply {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val iconUrl = toAssetsImage(asset.id_icon)
                        if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                            modifier = Modifier
                                .weight(0.1f)
                                .aspectRatio(1f),
                            model = iconUrl,
                            contentDescription = "essa é a moeda $name",
                            loading = {
                                CircularProgressIndicator()
                            },
                        )
                        else AsyncImage(
                            modifier = Modifier
                                .weight(0.1f)
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
                            Text(text = price_usd.toString())
                        }
                    }
                }
            }
        }
    }
}
