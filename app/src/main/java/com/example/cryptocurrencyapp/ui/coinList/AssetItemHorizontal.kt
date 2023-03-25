package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.abstraction.AssetsItem
import com.example.abstraction.listMockedAssetsItems
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.utils.formatDisplayedText
import com.example.cryptocurrencyapp.utils.greenColor
import com.example.cryptocurrencyapp.utils.toAssetsImage

@Preview(showBackground = true)
@Composable
private fun assetItemPreview() {
    AssetItemHorizontal(asset = listMockedAssetsItems[1])
}

@Composable
fun AssetItemHorizontal(asset: AssetsItem) {
    asset.apply {
        Column(modifier = Modifier.padding(8.dp).fillMaxHeight()) {
            val iconUrl = id_icon?.toAssetsImage()

            Row {
                if (!iconUrl.isNullOrEmpty()) {
                    SubcomposeAsyncImage(
                        modifier = Modifier.padding(4.dp),
                        model = iconUrl,
                        contentDescription = "essa é a moeda $name",
                        loading = {
                            CircularProgressIndicator(
                                color = Color(greenColor)
                            )
                        },
                    )
                    assetName()
                } else {
                    AsyncImage(
                        modifier = Modifier.padding(4.dp),
                        model = R.drawable.ic_coin_base,
                        contentDescription = "essa é a moeda $name",
                    )
                    assetName()
                }
            }

            val textValue = formatDisplayedText()

            Text(
                text = textValue,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun AssetsItem.assetName() {
    Column(modifier = Modifier.padding(4.dp)) {
        if (asset_id.isNotEmpty())
            Text(text = asset_id, textAlign = TextAlign.Center)
        else
            Text(text = name, textAlign = TextAlign.Center)
    }
}