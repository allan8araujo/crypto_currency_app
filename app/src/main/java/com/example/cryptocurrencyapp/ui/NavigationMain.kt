package com.example.cryptocurrencyapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencyapp.ui.coinList.CoinList
import com.example.cryptocurrencyapp.ui.favoriteCoin.FavoriteCoin
import com.example.cryptocurrencyapp.ui.favoriteCoinList.FavoriteCoinList

@Composable
fun NavigationMain(paddingValues: PaddingValues, navController: NavHostController) {

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavigationScreens.ListScreen.route
    ) {
        composable(route = NavigationScreens.ListScreen.route) {
            CoinList()
        }
        composable(route = NavigationScreens.FavoriteListScreen.route) {
            FavoriteCoinList()
        }
        composable(route = NavigationScreens.FavoriteScreen.route) {
            FavoriteCoin()
        }
    }
}