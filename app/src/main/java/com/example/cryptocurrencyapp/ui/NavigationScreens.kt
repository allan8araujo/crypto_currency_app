package com.example.cryptocurrencyapp.ui

sealed class NavigationScreens(val route: String) {
    object MainScreen : NavigationScreens("main_screen")
    object ListScreen : NavigationScreens("list_screen")
    object FavoriteListScreen : NavigationScreens("favorite_list_screen")
    object FavoriteScreen : NavigationScreens("favorite_screen")
}