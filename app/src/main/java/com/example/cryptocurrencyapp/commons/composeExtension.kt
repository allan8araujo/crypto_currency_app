package com.example.cryptocurrencyapp.commons

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun composeBackButton(navController: NavHostController?, route: String) {
    IconButton(onClick = { navController?.navigate(route) }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back button"
        )
    }
}