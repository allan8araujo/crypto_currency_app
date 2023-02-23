package com.example.cryptocurrencyapp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.ui.NavigationScreens

@Composable
fun Main(){
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { mutableStateOf(true) }

    when (backStackEntry){

    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Coin app") })},
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomNavigation {
                        val items = listOf(
                            NavigationScreens.MainScreen,
                            NavigationScreens.FavoriteListScreen,
                            NavigationScreens.ListScreen,
                            NavigationScreens.FavoriteScreen,
                        )

                        items.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painterResource(screen.Icon),
                                        contentDescription = null
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
                                }
                            )
                        }
                    }
                })
        }
    ) {
        Text(text = "text", modifier = Modifier.padding(it))
    }
}
