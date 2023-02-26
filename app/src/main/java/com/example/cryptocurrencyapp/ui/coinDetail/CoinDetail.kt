package com.example.cryptocurrencyapp.ui.coinDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cryptocurrencyapp.commons.composeBackButton
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.utils.toAssetsImage


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


    val backgroundCoinColor = Brush.verticalGradient(
        0.75f to Color(0xFF8D9562), 0.5f to Color(0xFF424242)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF424242))
    ) {

        Column(
            modifier = Modifier
                .weight(0.5f)
                .background(brush = backgroundCoinColor)
                .fillMaxWidth()
        ) {
            composeBackButton(
                navController = navController,
                route = NavigationScreens.ListScreen.route
            )
            Text(
                modifier = Modifier
                    .weight(0.1f)
                    .align(Alignment.CenterHorizontally),
                text = coinNameState.toString()
            )
            AsyncImage(
                modifier = Modifier
                    .weight(0.2f)
                    .align(Alignment.CenterHorizontally),
                model = toAssetsImage(asset?.id_icon),
                contentDescription = "imagem da moeda ${asset?.name.toString()}"
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = coinPriceState.toString())
            Text(text = coinVolumeHoursState.toString())
            Text(text = coinVolumeDayState.toString())
            Text(text = coinVolumeMonthState.toString())
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), onClick = { /*TODO*/ }) {
            Text(text = "Adicionar")
        }
    }
}

