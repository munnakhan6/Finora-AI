package com.pgtech.finora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.pgtech.finora.navigation.NavGraph
import com.pgtech.finora.presentation.splash.SplashViewModel
import com.pgtech.finora.ui.theme.FinoraTheme

class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install Android 12+ Splash API
        val splashScreen = installSplashScreen()
        
        super.onCreate(savedInstanceState)

        // Custom transition: We keep the splash API visible 
        // until we decide to show our Compose Splash
        splashScreen.setKeepOnScreenCondition { false }

        enableEdgeToEdge()
        
        // Make system bars transparent for full screen gradient effect
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FinoraTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    splashViewModel = splashViewModel
                )
            }
        }
    }
}
