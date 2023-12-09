package com.dhozhenkohealthdatademo.domain.navigation

sealed class NavigationRoute(val name: String) {
    object WelcomeScreenNavigationRoute: NavigationRoute(name = "welcome_screen_navigation_route")
    object HealthDataScreenNavigationRoute: NavigationRoute(name = "health_data_screen_navigation_route")
    object HealthDetailScreenNavigationRoute: NavigationRoute(name = "health_detail_screen_navigation_route")
}