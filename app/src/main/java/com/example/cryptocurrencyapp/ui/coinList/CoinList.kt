package com.example.cryptocurrencyapp.ui.coinList

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.utils.*
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinList(
    coinViewModel: CoinListViewModel,
    coinDetailSharedViewModel: CoinDetailSharedViewModel,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val stateCoin = remember { mutableStateOf<CoinListState?>(null) }
    val favoriteAssets = coinViewModel.allFavoriteAssets.collectAsState(initial = null)

    val filterTypeState = coinViewModel.filterType.value

    LaunchedEffect(key1 = filterTypeState) {
        Log.i("filterType", "filterType:${filterTypeState.type}")
        scope.launch {
            coinViewModel.filterByType(stateCoin)
        }
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            coinViewModel.assetsLiveData.collect { result ->
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

        Text(
            modifier = Modifier.fillMaxWidth(), text = currentDate, textAlign = TextAlign.Center
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            val stateCoin_ = stateCoin.value

            if (stateCoin_?.isSucess != null) items(stateCoin_.isSucess!!) { asset ->
                val isFavorite =
                    favoriteAssets.value?.any { it.name == asset.name } == true

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), onClick = {

                    coinDetailSharedViewModel.setIsFavorite(isFavorite)

                    coinDetailSharedViewModel.addCoin(asset)
                    navController.navigate(NavigationScreens.CoinDetailScreen.route)
                }) {
                    asset.apply {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
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

                            val textValue = formatNullText()

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
            }
        }
    }
}

