package com.test.mazetv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavGraph(navController: NavHostController) {
  NavHost(
      navController = navController,
      startDestination = NavRoute.View.path,
  ) {
    composable(NavRoute.View.path) {}

    composable(
        route = NavRoute.Details.path,
        arguments =
            listOf(
                navArgument("showsId") { type = NavType.IntType },
            ),
    ) {}
  }
}
