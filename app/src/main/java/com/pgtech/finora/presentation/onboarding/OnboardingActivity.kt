package com.pgtech.finora.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pgtech.finora.R
import com.pgtech.finora.data.local.SharedPrefManager
import com.pgtech.finora.databinding.ActivityOnboardingBinding
import com.pgtech.finora.presentation.auth.LoginActivity
import com.pgtech.finora.presentation.auth.RegisterActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        sharedPrefManager = SharedPrefManager(this)
        if (sharedPrefManager.isOnboardingCompleted()) {
            navigateToLogin()
            return
        }

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupListeners()
        observeViewModel()
    }

    private fun setupViewPager() {
        viewModel.onboardingItems.observe(this) { items ->
            val adapter = OnboardingAdapter(items)
            binding.viewPager.adapter = adapter
            
            TabLayoutMediator(binding.indicatorLayout, binding.viewPager) { tab, position ->
                // Indicators are handled via drawable selectors in TabLayout
            }.attach()
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)
                updateUI(position)
                playAnimations()
            }
        })
    }

    private fun setupListeners() {
        binding.btnSkip.setOnClickListener {
            navigateToLogin()
        }

        binding.btnGetStarted.setOnClickListener {
            if (binding.viewPager.currentItem < 2) {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            } else {
                completeOnboarding()
                navigateToRegister()
            }
        }

        binding.tvLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun observeViewModel() {
        viewModel.currentPage.observe(this) { page ->
            // Update UI if needed from VM
        }
    }

    private fun updateUI(position: Int) {
        if (position == 2) {
            binding.btnSkip.visibility = View.GONE
            binding.btnGetStarted.text = getString(R.string.get_started)
        } else {
            binding.btnSkip.visibility = View.VISIBLE
            binding.btnGetStarted.text = "Next" // Or keep it as Get Started if preferred
        }
    }

    private fun playAnimations() {
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.btnGetStarted.startAnimation(fadeIn)
        binding.indicatorLayout.startAnimation(fadeIn)
    }

    private fun completeOnboarding() {
        sharedPrefManager.setOnboardingCompleted(true)
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}