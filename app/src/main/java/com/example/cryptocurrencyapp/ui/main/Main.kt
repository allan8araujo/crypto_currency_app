package com.example.cryptocurrencyapp.ui.main

import android.app.Application
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.commons.FilterCryptoMenu
import com.example.cryptocurrencyapp.commons.ubuntuBold
import com.example.cryptocurrencyapp.ui.NavigationBarScreens
import com.example.cryptocurrencyapp.ui.NavigationMain
import com.example.cryptocurrencyapp.ui.NavigationScreens
import com.example.cryptocurrencyapp.utils.FilterEnum
import com.example.cryptocurrencyapp.utils.FilterEnum.Companion.ALL_CURRENCIES
import com.example.cryptocurrencyapp.utils.FilterEnum.Companion.CRYPTO_CURRENCIES
import com.example.cryptocurrencyapp.utils.FilterEnum.Companion.TRADITIONAL_CURRENCIES
import com.example.cryptocurrencyapp.utils.iceWhiteColor
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.factories.ListViewModelFactory

@Composable
fun Main() {
    val navController = rememberNavController()

    val bottomBarState = rememberSaveable { mutableStateOf(true) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val titleTextState = remember { mutableStateOf("") }

    val context = LocalContext.current
    val coinViewModel: CoinListViewModel = viewModel(
        factory = ListViewModelFactory(context.applicationContext as Application)
    )

    titleTextState.value = "Vellorum"

    when (currentDestination?.route) {
        NavigationScreens.CoinDetailScreen.route -> bottomBarState.value = false

        else -> {
            bottomBarState.value = true
        }
    }

    Scaffold(topBar = {
        if (bottomBarState.value) {
            TopAppBar(title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    text = titleTextState.value,
                    textAlign = TextAlign.Start,
                    fontFamily = ubuntuBold,
                    fontSize = 24.sp,
                    color = Color(iceWhiteColor)
                )
            }, actions = {
                FilterCryptoMenu(
                    items = listOf(CRYPTO_CURRENCIES, TRADITIONAL_CURRENCIES, ALL_CURRENCIES),
                    onItemSelected = { selectedItem ->
                        when (selectedItem) {
                            CRYPTO_CURRENCIES -> coinViewModel.filterType.value =
                                FilterEnum.cryptoCurrencies
                            TRADITIONAL_CURRENCIES -> coinViewModel.filterType.value =
                                FilterEnum.traditionalCurrencies
                            ALL_CURRENCIES -> coinViewModel.filterType.value =
                                FilterEnum.allCurrencies
                        }
                    }
                )
            })
        }
    }, bottomBar = {
        if (bottomBarState.value) {
            BottomNavigation {
                val items = listOf(
                    NavigationBarScreens.ListScreen,
                    NavigationBarScreens.FavoriteListScreen,
                )

                items.forEach { screen ->
                    BottomNavigationItem(icon = {
                        Icon(
                            painterResource(screen.Icon), contentDescription = null
                        )
                    },
                        label = { Text(screen.resourceId) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        }
    }) { paddingValues ->
        NavigationMain(paddingValues, navController, coinViewModel)
    }
}
