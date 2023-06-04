package com.example.cryptocurrencyapp.presentation.ui

import androidx.annotation.DrawableRes
import com.example.cryptocurrencyapp.R

sealed class NavigationScreens(val route: String) {
    object CoinDetailScreen : NavigationScreens("favorite_screen")
    object ListScreen : NavigationScreens("list_screen")
    object FavoriteListScreen : NavigationScreens("favorite_list_screen")
}

sealed class NavigationBarScreens(
    val route: String,
    val resourceId: String,
    @DrawableRes val Icon: Int
) {
    object ListScreen :
        NavigationBarScreens(
            route = "list_screen",
            resourceId = "coin list",
            Icon = R.drawable.ic_baseline_attach_money_24
        )

    object FavoriteListScreen : NavigationBarScreens(
        route = "favorite_list_screen",
        resourceId = "favorite list",
        Icon = R.drawable.ic_baseline_star_coin_24
    )
}
