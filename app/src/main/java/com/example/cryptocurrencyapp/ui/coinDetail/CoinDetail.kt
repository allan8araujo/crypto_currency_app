package com.example.cryptocurrencyapp.ui.coinDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.commons.composeBackButton
import com.example.cryptocurrencyapp.commons.textButtonStyle
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.utils.*
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel

@Composable
fun CoinDetail(
    coinViewModel: CoinListViewModel,
    coinDetailSharedViewModel: CoinDetailSharedViewModel,
    navController: NavHostController
) {
    val asset = coinDetailSharedViewModel.selectedCoin
    val isFavoriteAsset = coinDetailSharedViewModel.isFavoriteAsset
    val backgroundCoinColor = Brush.verticalGradient(
        0f to Color(greenColor),
        0.20f to Color(greenColor),
        0.20f to Color(lightBlackColor),
        1f to Color(lightBlackColor)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundCoinColor)
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                composeBackButton(
                    navController = navController,
                    route = NavigationScreens.ListScreen.route
                )
            }

            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(0.3f)
                    .align(Alignment.CenterHorizontally),
                model = asset?.id_icon?.toAssetsImage(),
                contentDescription = "imagem da moeda ${asset?.name.toString()}"
            )

            Row(modifier = Modifier.weight(0.1f)) {
                if (isFavoriteAsset) {
                    Image(
                        modifier = Modifier
                            .aspectRatio(1f),
                        painter = painterResource(id = R.drawable.ic_baseline_star_coin_24),
                        contentDescription = "is favorite"
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .aspectRatio(1f),
                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
                        contentDescription = "is not favorite"
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = asset?.name.toString(),
                    style = MaterialTheme.typography.h4,
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            val textValue = asset?.formatNullText()

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = textValue.toString(),
                style = MaterialTheme.typography.h3,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Volume (24h)",
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
            Text(
                text = asset?.volume_1day_usd?.toMoneyFormat().toString(),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = "Volume (30d)",
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
            Text(
                modifier = Modifier.weight(0.5f),
                text = asset?.volume_1mth_usd?.toMoneyFormat().toString(),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )

            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(greenColor),
                    contentColor = Color.White
                ),
                onClick = {
                    asset?.let { asset_ ->
                        coinDetailSharedViewModel.setIsFavorite(!isFavoriteAsset)
                        if (isFavoriteAsset)
                            coinViewModel.deleteAsset(asset_) else
                            coinViewModel.insertAsset(asset_)
                    }
                }
            ) {
                val buttonTextState = if (!isFavoriteAsset) "ADD" else "REMOVE"
                textButtonStyle(buttonTextState)
            }
        }
    }
}