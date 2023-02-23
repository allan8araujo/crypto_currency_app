package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import com.example.abstraction.AssetsItem
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.states.DataResult
import kotlinx.coroutines.launch

@Composable
fun CoinList(coinViewModel: CoinListViewModel) {
    val coinListStateRequest =
        coinViewModel.assetsFromResultApi.asFlow().collectAsState(initial = null)
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "coinList")
        Button(onClick = { scope.launch { coinViewModel.getAllAssets() } }) {}
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (coinListStateRequest.value is DataResult.Success)
                items((coinListStateRequest.value as DataResult.Success<List<AssetsItem>>).data) { asset ->
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
}
