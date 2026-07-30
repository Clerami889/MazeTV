package com.test.mazetv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.test.mazetv.Main.details.View.ShowDetailsView
import com.test.mazetv.Main.shows.View.ShowView

@Composable
fun NavGraph(navController: NavHostController) {
  NavHost(
      navController = navController,
      startDestination = NavRoute.View.path,
  ) {
    composable(NavRoute.View.path) {
      ShowView(
          onShowClick = { showsId ->
            navController.navigate(NavRoute.Details.createRoute(showsId))
          }
      )
    }

    composable(
        route = NavRoute.Details.path,
        arguments =
            listOf(
                navArgument("showsId") { type = NavType.IntType },
            ),
    ) {
      ShowDetailsView(
          onBackClick = {
            navController.popBackStack()
          }
      )
    }
  }
}
