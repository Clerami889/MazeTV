package com.test.mazetv.navigation

sealed class NavRoute(val path: String) {

  object View : NavRoute("view")

  object Details : NavRoute("details/{showsId}") {
    fun createRoute(showsId: Int) = "details/$showsId"
  }
}
