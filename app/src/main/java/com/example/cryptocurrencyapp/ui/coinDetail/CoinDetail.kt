@file:Suppress("NAME_SHADOWING")

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.abstraction.AssetsItem
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
    val backgroundCoinColor = getGradientBackground()

    CoinDetail(
        navController = navController,
        asset = asset,
        isFavoriteAsset = isFavoriteAsset,
        deleteAsset = { asset ->
            coinViewModel.deleteAsset(asset)
        },
        insertAsset = { asset ->
            coinViewModel.insertAsset(asset)
        },
        setIsFavorite = { boolean ->
            coinDetailSharedViewModel.setIsFavorite(boolean)
        },
        backgroundCoinColor = backgroundCoinColor
    )
}

@Composable
private fun getGradientBackground() = Brush.verticalGradient(
    0f to Color(greenColor),
    0.20f to Color(greenColor),
    0.20f to Color(lightBlackColor),
    1f to Color(lightBlackColor)
)

@Composable
fun CoinDetail(
    navController: NavHostController,
    asset: AssetsItem?,
    isFavoriteAsset: Boolean,
    deleteAsset: (AssetsItem) -> Unit,
    insertAsset: (AssetsItem) -> Unit,
    setIsFavorite: (Boolean) -> Unit,
    backgroundCoinColor: Brush
) {
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

            if (asset?.id_icon.isNullOrEmpty()) AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(0.3f)
                    .align(Alignment.CenterHorizontally),
                model = R.drawable.ic_coin_base,
                contentDescription = "essa é a moeda ${asset?.name}",
            )
            else AsyncImage(
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
            val textValue = asset?.formatDisplayedText()

            Text(
                text = textValue.toString(),
                style = MaterialTheme.typography.h3,
                color = Color.White
            )
            Text(
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
                text = "Volume (30d)",
                style = MaterialTheme.typography.h6,
                color = Color.Gray
            )
            Text(
                text = asset?.volume_1mth_usd?.toMoneyFormat().toString(),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(0.2f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(greenColor),
                contentColor = Color.White
            ),
            onClick = {
                asset?.let { asset_ ->
                    setIsFavorite(!isFavoriteAsset)
                    if (isFavoriteAsset)
                        deleteAsset(asset_) else
                        insertAsset(asset_)
                }
            }
        ) {
            val buttonTextState = if (!isFavoriteAsset) "ADD" else "REMOVE"
            textButtonStyle(buttonTextState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinDetailPreview() {
    CoinDetail(
        navController = rememberNavController(),
        asset = AssetsItem(
            asset_id = "1",
            name = "Bitcoin",
            price_usd = 48281.39,
            volume_1hrs_usd = 102480000.0,
            volume_1day_usd = 10248000000.0,
            volume_1mth_usd = 119601000000.0,
            id_icon = "https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/btc.svg",
            data_symbols_count = 0,
            type_is_crypto = 0,
            data_end = null,
            data_orderbook_end = null,
            data_orderbook_start = null,
            data_quote_end = null,
            data_quote_start = null,
            data_start = null,
            data_trade_end = null,
            data_trade_start = null
        ),
        isFavoriteAsset = true,
        deleteAsset = { asset -> },
        insertAsset = { asset -> },
        setIsFavorite = { boolean -> },
        backgroundCoinColor = getGradientBackground()
    )
}