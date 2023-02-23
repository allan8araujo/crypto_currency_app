package com.example.cryptocurrencyapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.ui.coinList.CoinList
import com.example.cryptocurrencyapp.ui.favoriteCoin.FavoriteCoin
import com.example.cryptocurrencyapp.ui.favoriteCoinList.FavoriteCoinList
import com.example.cryptocurrencyapp.view.Main

@Composable
fun NavigationMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreens.MainScreen.route) {
        composable(route = NavigationScreens.MainScreen.route) {
            Main()
        }
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