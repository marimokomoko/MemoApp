package com.example.memoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.memoapp.ui.home.HomeDestination
import com.example.memoapp.ui.home.HomeScreen
import com.example.memoapp.ui.item.ItemEditDestination
import com.example.memoapp.ui.item.ItemEditScreen
import com.example.memoapp.ui.item.ItemEntryDestination
import com.example.memoapp.ui.item.ItemEntryScreen

/**
 * TodoNavHost:ナビゲーション管理コンポーネント
 */
@Composable
fun TodoNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        // NavGraphBuilder.composable関数:特定ルートと関連するコンポーザブルをナビグラフに追加する
        composable(route = HomeDestination.route) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(ItemEntryDestination.route)
            }, navigateToItemUpdate = {
                navController.navigate("${ItemEditDestination.route}/${it}")
            })
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(navigateBack = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }, onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = {
                if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }
            }, onNavigateUp = { navController.navigateUp() })
        }
    }
}