package com.pgtech.finora.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Idle)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        startSplashTimer()
    }

    private fun startSplashTimer() {
        viewModelScope.launch {
            // Splash duration: 2500ms
            delay(2500)
            
            // For now, navigating to onboarding as default
            // In future, this logic will check Auth/FirstLaunch state
            _uiState.value = SplashUiState.NavigateTo("onboarding")
        }
    }
}
