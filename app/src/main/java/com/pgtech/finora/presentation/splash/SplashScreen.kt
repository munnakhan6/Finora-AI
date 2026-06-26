package com.pgtech.finora.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pgtech.finora.R
import com.pgtech.finora.ui.theme.SplashGradientBottom
import com.pgtech.finora.ui.theme.SplashGradientTop

@Composable
fun SplashScreen(
    onNavigate: (String) -> Unit,
    viewModel: SplashViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    LaunchedEffect(key1 = uiState) {
        if (uiState is SplashUiState.NavigateTo) {
            onNavigate((uiState as SplashUiState.NavigateTo).destination)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(SplashGradientTop, SplashGradientBottom)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Animation
            AnimatedVisibility(
                visible = startAnimation,
                enter = fadeIn(animationSpec = tween(800)) +
                        scaleIn(initialScale = 0.8f, animationSpec = tween(800))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.finora_round),
                    contentDescription = "Finora Logo",
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Title Animation
            AnimatedVisibility(
                visible = startAnimation,
                enter = fadeIn(animationSpec = tween(800, delayMillis = 200)) +
                        slideInVertically(
                            initialOffsetY = { 40 },
                            animationSpec = tween(800, delayMillis = 200)
                        )
            ) {
                Text(
                    text = "Finora AI",
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White
                )
            }

            // Tagline Animation
            AnimatedVisibility(
                visible = startAnimation,
                enter = fadeIn(animationSpec = tween(800, delayMillis = 400)) +
                        slideInVertically(
                            initialOffsetY = { 40 },
                            animationSpec = tween(800, delayMillis = 400)
                        )
            ) {
                Text(
                    text = "Your Smart Money Companion",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // Bottom Loading & Version
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "v1.0.0",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f)
            )
        }
    }
}
