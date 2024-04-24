package com.example.memoapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.memoapp.ui.navigation.TodoNavHost

/**
 * TodoApp:NavHostを取り込むコンポーザブル関数
 */
@Composable
fun TodoApp(
    navController: NavHostController = rememberNavController()
) {
    TodoNavHost(navController = navController, modifier = Modifier)
}