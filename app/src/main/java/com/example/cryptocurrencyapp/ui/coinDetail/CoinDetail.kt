package com.example.cryptocurrencyapp.ui.coinDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.example.cryptocurrencyapp.utils.greenColor
import com.example.cryptocurrencyapp.utils.lightBlack
import com.example.cryptocurrencyapp.utils.toAssetsImage
import com.example.cryptocurrencyapp.utils.toMoneyFormat
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel


@Composable
fun CoinDetail(
    coinViewModel: CoinListViewModel,
    coinDetailSharedViewModel: CoinDetailSharedViewModel,
    navController: NavHostController
) {
    val asset = coinDetailSharedViewModel.selectedCoin
    val coinNameState = asset?.name
    val coinPriceState = asset?.price_usd?.toMoneyFormat()
    val coinVolumeHoursState = asset?.volume_1hrs_usd
    val coinVolumeDayState = asset?.volume_1day_usd
    val coinVolumeMonthState = asset?.volume_1mth_usd


    val backgroundCoinColor = Brush.verticalGradient(
        0.75f to Color(greenColor), 0.5f to Color(lightBlack)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(lightBlack))
    ) {

        Column(
            modifier = Modifier
                .weight(0.5f)
                .background(brush = backgroundCoinColor)
                .fillMaxWidth()
        ) {
            composeBackButton(
                navController = navController, route = NavigationScreens.ListScreen.route
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
                model = asset?.id_icon?.toAssetsImage(),
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
            .padding(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(greenColor)),
            onClick = {
                asset?.let { asset_ ->
                    coinViewModel.insertAsset(asset_)
                }
            }) {
            Text(text = "Adicionar")
        }
    }
}


