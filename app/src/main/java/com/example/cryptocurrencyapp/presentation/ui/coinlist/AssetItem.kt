package com.example.cryptocurrencyapp.presentation.ui.coinlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.abstraction.AssetsItem
import com.example.abstraction.toAssetsItem
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.assets.listMockedAssetsItems
import com.example.cryptocurrencyapp.commons.utils.formatDisplayedText
import com.example.cryptocurrencyapp.commons.utils.greenColor
import com.example.cryptocurrencyapp.commons.utils.toAssetsImage

@Preview(showBackground = true)
@Composable
private fun assetItemPreview() {
    AssetItem(asset = listMockedAssetsItems[1].toAssetsItem(), isFavorite = false)
}

@Composable
fun AssetItem(asset: AssetsItem, isFavorite: Boolean = false) {
    asset.apply {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val iconUrl = id_icon?.toAssetsImage()
            if (!iconUrl.isNullOrEmpty()) SubcomposeAsyncImage(
                modifier = Modifier
                    .weight(0.4f)
                    .aspectRatio(1f),
                model = iconUrl,
                contentDescription = "essa é a moeda $name",
                loading = {
                    CircularProgressIndicator(
                        color = Color(greenColor)
                    )
                },
            )
            else AsyncImage(
                modifier = Modifier
                    .weight(0.4f)
                    .aspectRatio(1f),
                model = R.drawable.ic_coin_base,
                contentDescription = "essa é a moeda $name",
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = name)
                Text(text = asset_id)
            }

            val textValue = formatDisplayedText()

            Text(
                text = textValue,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )

            if (isFavorite) {
                Image(
                    modifier = Modifier
                        .weight(0.2f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "essa é a moeda $name",
                )
            } else {
                Image(
                    modifier = Modifier
                        .weight(0.2f)
                        .aspectRatio(1f),
                    painter = painterResource(id = R.drawable.ic_outlined_heart),
                    contentDescription = "essa é a moeda $name",
                )
            }
        }
    }
}
