package com.example.cryptocurrencyapp.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.ui.NavigationBarScreens
import com.example.cryptocurrencyapp.ui.NavigationMain
import com.example.cryptocurrencyapp.ui.NavigationScreens

@Composable
fun Main() {
    val navController = rememberNavController()

    val bottomBarState = rememberSaveable { mutableStateOf(true) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val titleTextState = remember { mutableStateOf("")}

    when (currentDestination?.route) {
        NavigationScreens.CoinDetailScreen.route -> bottomBarState.value = false
        NavigationScreens.FavoriteListScreen.route -> titleTextState.value = "Favorites crypto currencies"
        NavigationScreens.ListScreen.route -> titleTextState.value = "List of crypto currencies"
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
                        .padding(8.dp),
                    text = titleTextState.value,
                    textAlign = TextAlign.Center,
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
        NavigationMain(paddingValues, navController)
    }
}
