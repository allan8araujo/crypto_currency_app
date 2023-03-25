package com.example.cryptocurrencyapp.commons

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cryptocurrencyapp.R

@Composable
fun composeBackButton(
    navController: NavHostController?,
    route: String
) {
    IconButton(onClick = { navController?.navigate(route) }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back button"
        )
    }
}

@Composable
fun textButtonStyle(buttonTextState: String) {
    Text(
        text = buttonTextState,
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

