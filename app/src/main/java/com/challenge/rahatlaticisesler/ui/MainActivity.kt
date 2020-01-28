package com.challenge.rahatlaticisesler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.challenge.rahatlaticisesler.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavController()
    }

    private fun initNavController() {
        val navController = findNavController(R.id.main_fragment)

        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.favoritesFragment,
                R.id.categoriesFragment
            )
            .build()

        bottom_nav.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }
}
