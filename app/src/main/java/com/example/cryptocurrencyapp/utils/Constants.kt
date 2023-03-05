package com.example.cryptocurrencyapp.utils

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

const val greenColor = 0xFF8D9562
const val lightBlackColor = 0xFF424242
const val lightBlack_2Color = 0xFF2B2B2B
const val transparentColor = 0x00000000

val customColor = Colors(
    primary = Color(lightBlackColor),
    primaryVariant = Color(greenColor),
    secondary = Color(greenColor),
    secondaryVariant = Color(greenColor),
    background = Color(lightBlackColor),
    surface = Color(lightBlack_2Color),
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Red,
    isLight = true,
)