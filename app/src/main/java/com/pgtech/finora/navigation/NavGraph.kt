package com.pgtech.finora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pgtech.finora.presentation.splash.SplashScreen
import com.pgtech.finora.presentation.splash.SplashViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    splashViewModel: SplashViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                viewModel = splashViewModel,
                onNavigate = { destination ->
                    navController.navigate(destination) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        
        // Placeholders for future screens
        composable("onboarding") { /* Onboarding Screen Placeholder */ }
        composable("login") { /* Login Screen Placeholder */ }
        composable("dashboard") { /* Dashboard Screen Placeholder */ }
    }
}
