package com.example.cryptocurrencyapp.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencyapp.presentation.ui.coindetail.CoinDetail
import com.example.cryptocurrencyapp.presentation.ui.coindetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.presentation.ui.coinlist.CoinList
import com.example.cryptocurrencyapp.presentation.ui.coinlist.CoinListViewModel
import com.example.cryptocurrencyapp.presentation.ui.favoritecoinlist.FavoriteCoinList

@Composable
fun RootNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController,
    coinViewModel: CoinListViewModel,
) {
    val coinDetailSharedViewModel: CoinDetailSharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.ListScreen.route
    ) {
        composable(route = NavigationScreens.ListScreen.route) {
            CoinList(
                coinViewModel = coinViewModel,
                coinDetailSharedViewModel = coinDetailSharedViewModel,
                navController = navController
            )
        }
        composable(route = NavigationScreens.CoinDetailScreen.route) {
            CoinDetail(
                coinViewModel = coinViewModel,
                coinDetailSharedViewModel = coinDetailSharedViewModel,
                navController = navController
            )
        }
        composable(route = NavigationScreens.FavoriteListScreen.route) {
            FavoriteCoinList(
                navController = navController,
                coinViewModel = coinViewModel,
                coinDetailSharedViewModel = coinDetailSharedViewModel,
            )
        }
    }
}
