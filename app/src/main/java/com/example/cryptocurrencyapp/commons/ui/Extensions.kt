package com.example.cryptocurrencyapp.commons.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.commons.utils.iceWhiteColor_30
import com.example.cryptocurrencyapp.commons.utils.iceWhiteColor_50

@Composable
fun CustomBackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back button"
        )
    }
}

@Composable
fun CustomText(customText: String) {
    Text(
        text = customText,
        fontSize = 16.sp
    )
}

/**
 * Fonts families used to style texts
 * **/

val poppinsRegular = FontFamily(Font(R.font.poppins_regular))
val robotoRegular = FontFamily(Font(R.font.roboto))
val nunitoRegular = FontFamily(Font(R.font.nunito_sans_light))
val ubuntuRegular = FontFamily(Font(R.font.ubuntu_regular))
val ubuntuBold = FontFamily(Font(R.font.ubuntu_bold))

val whiteBlackGradientColor = Brush.linearGradient(
    colors = listOf(
        Color(iceWhiteColor_30),
        Color(0xFF1A1A1A)
    ),
    start = Offset.Zero,
    end = Offset.Infinite
)

val blackWhiteGradientColor = Brush.linearGradient(
    colors = listOf(
        Color(0xFF1A1A1A),
        Color(iceWhiteColor_50),
    ),
    start = Offset.Zero,
    end = Offset.Infinite
)
