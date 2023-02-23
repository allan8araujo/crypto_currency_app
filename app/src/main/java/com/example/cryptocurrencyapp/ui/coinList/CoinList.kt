package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import kotlinx.coroutines.launch

@Composable
fun CoinList(coinViewModel: CoinListViewModel) {
    val scope = rememberCoroutineScope()
    val coinListState = coinViewModel.assetsLiveData.collectAsState(initial = null)
    val state = coinListState.value

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "coinList")
        Button(onClick = { scope.launch { coinViewModel.getAllAssets() } }) {}
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (state?.isSucess != null) items(state.isSucess!!) { asset ->
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
    if (state?.isLoading == true) CircularProgressIndicator()
}

