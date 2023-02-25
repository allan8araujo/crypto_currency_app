package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apilibrary.repository.states.DataResult
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import kotlinx.coroutines.launch

@Composable
fun CoinList(coinViewModel: CoinListViewModel) {
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

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "coinList")
        Button(onClick = { scope.launch { coinViewModel.getAllAssets() } }) {}
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            val stateCoin_ = stateCoin.value
            if (stateCoin_?.isSucess != null) items(stateCoin_.isSucess!!) { asset ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    asset.apply {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
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
    if (stateCoin.value?.isLoading == true) CircularProgressIndicator()
}

//
//
//@Preview
//@Composable
//fun CoinListPreview() {
//    CoinList()
//}