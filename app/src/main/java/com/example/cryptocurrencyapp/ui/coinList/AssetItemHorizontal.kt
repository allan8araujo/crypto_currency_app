package com.example.cryptocurrencyapp.ui.coinList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val iconUrl = id_icon?.toAssetsImage()
            if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                model = iconUrl,
                contentDescription = "essa é a moeda $name",
                loading = {
                    CircularProgressIndicator(
                        color = Color(greenColor)
                    )
                },
            )
            else AsyncImage(
                model = R.drawable.ic_coin_base,
                contentDescription = "essa é a moeda $name",
            )
            Column {
                Text(text = name)
                Text(text = asset_id)
            }

            val textValue = formatDisplayedText()

            Text(
                text = textValue,
                textAlign = TextAlign.End
            )
        }
    }
}