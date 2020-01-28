package com.challenge.rahatlaticisesler.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.challenge.rahatlaticisesler.ui.MainActivity
import com.challenge.rahatlaticisesler.utils.Constants.Companion.SPLASH_DELAY

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        simulateProgress()
    }

    private fun simulateProgress() {
        Handler().postDelayed({
            MainActivity.start(this@SplashActivity)
        }, SPLASH_DELAY)
    }
}