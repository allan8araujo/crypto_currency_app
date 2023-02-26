package com.example.cryptocurrencyapp.ui.coinDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cryptocurrencyapp.commons.composeBackButton
import com.example.cryptocurrencyapp.ui.NavigationScreens


@Composable
fun CoinDetail(
    coinDetailSharedViewModel: CoinDetailSharedViewModel, navController: NavHostController
) {
    val asset = coinDetailSharedViewModel.selectedCoin

    val coinNameState = asset?.name
    val coinPriceState = asset?.price_usd
    val coinVolumeHoursState = asset?.volume_1hrs_usd
    val coinVolumeDayState = asset?.volume_1day_usd
    val coinVolumeMonthState = asset?.volume_1mth_usd

    Column(modifier = Modifier.fillMaxSize()) {
        composeBackButton(navController = navController, route = NavigationScreens.ListScreen.route)
        Text(text = coinNameState.toString())
        Text(text = coinPriceState.toString())
        Text(text = coinVolumeHoursState.toString())
        Text(text = coinVolumeDayState.toString())
        Text(text = coinVolumeMonthState.toString())
    }
}
