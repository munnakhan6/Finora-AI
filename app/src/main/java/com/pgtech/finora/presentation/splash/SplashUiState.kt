package com.pgtech.finora.presentation.splash

sealed class SplashUiState {
    object Idle : SplashUiState()
    data class NavigateTo(val destination: String) : SplashUiState()
}
