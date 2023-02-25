package com.example.cryptocurrencyapp.ui

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.cryptocurrencyapp.ui.coinList.CoinList
import com.example.cryptocurrencyapp.ui.favoriteCoin.FavoriteCoin
import com.example.cryptocurrencyapp.ui.favoriteCoinList.FavoriteCoinList
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.factories.ListViewModelFactory

@Composable
fun NavigationMain(
    paddingValues: PaddingValues,
    navController: NavHostController,
    context: Context,
    viewModel: CoinListViewModel = viewModel(
        factory = ListViewModelFactory(Repository(AssetsDatabase.getDatabase(context).assetsDao()))
    )
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavigationScreens.ListScreen.route
    ) {
        composable(route = NavigationScreens.ListScreen.route) { CoinList(viewModel) }
        composable(route = NavigationScreens.FavoriteListScreen.route) { FavoriteCoinList() }
        composable(route = NavigationScreens.FavoriteScreen.route) { FavoriteCoin() }
    }
}