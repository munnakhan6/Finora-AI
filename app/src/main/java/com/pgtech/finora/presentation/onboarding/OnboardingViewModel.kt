package com.pgtech.finora.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pgtech.finora.R

class OnboardingViewModel : ViewModel() {

    private val _onboardingItems = MutableLiveData<List<OnboardingItem>>()
    val onboardingItems: LiveData<List<OnboardingItem>> = _onboardingItems

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    init {
        loadOnboardingItems()
    }

    private fun loadOnboardingItems() {
        val items = listOf(
            OnboardingItem(
                R.drawable.ic_onboarding_ai,
                "Smart AI Financial Advisor",
                "Get personalized money tips powered by AI."
            ),
            OnboardingItem(
                R.drawable.ic_onboarding_wallet,
                "Track Every Transaction",
                "Monitor income, expenses and savings in one place."
            ),
            OnboardingItem(
                R.drawable.ic_onboarding_goal,
                "Achieve Your Goals Faster",
                "Set savings goals and let Finora AI guide you."
            )
        )
        _onboardingItems.value = items
    }

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }
}