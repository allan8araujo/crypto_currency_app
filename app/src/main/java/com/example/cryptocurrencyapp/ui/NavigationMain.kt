package com.example.cryptocurrencyapp.ui

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetail
import com.example.cryptocurrencyapp.ui.coinDetail.CoinDetailSharedViewModel
import com.example.cryptocurrencyapp.ui.coinList.CoinList
import com.example.cryptocurrencyapp.ui.favoriteCoinList.FavoriteCoinList
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.factories.ListViewModelFactory

@Composable
fun NavigationMain(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val coinDetailSharedViewModel: CoinDetailSharedViewModel = viewModel()
    val coinViewModel: CoinListViewModel = viewModel(
        factory = ListViewModelFactory(context.applicationContext as Application)
    )

    NavHost(
        modifier = Modifier.padding(paddingValues),
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