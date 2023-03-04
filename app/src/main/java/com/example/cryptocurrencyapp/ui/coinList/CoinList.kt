package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.utils.greenColor
import com.example.cryptocurrencyapp.utils.toAssetsImage
import com.example.cryptocurrencyapp.utils.toMoneyFormat
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
            .padding(32.dp)
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
                            val iconUrl = id_icon?.toAssetsImage()
                            if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                                modifier = Modifier
                                    .weight(0.1f)
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
                                Text(text = price_usd?.toMoneyFormat().toString())
                            }
                        }
                    }
                }
            }
        }
    }
}