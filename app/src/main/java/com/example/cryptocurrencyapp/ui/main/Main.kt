package com.example.cryptocurrencyapp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
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

    when (currentDestination?.route) {
        NavigationScreens.FavoriteScreen.route -> bottomBarState.value = false
        else -> bottomBarState.value = true
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Coin app") }) },
        bottomBar = {
            AnimatedVisibility(visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
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
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                })
                        }
                    }
                })
        }) { paddingValues ->
        NavigationMain(paddingValues, navController)
    }
}
