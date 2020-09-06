package com.example.eldho.bottomnavigationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect bottom navigation view to with our navigation component
        bottomNavigationView.setupWithNavController(nav_host_fragment.findNavController())

        setUpActionBarNames()

        setUpBadge()
    }

    private fun setUpActionBarNames() {
        //NOTE : For changing names in the actionBar , go to nav graph
        //      and change  `android:label="home"`
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.emailFragment, R.id.noteFragment))
        setupActionBarWithNavController(nav_host_fragment.findNavController(), appBarConfiguration)
    }

    private fun setUpBadge() {
        bottomNavigationView.getOrCreateBadge(R.id.homeFragment).apply {
            number = 5 // for number badge
            isVisible = true
        }
        bottomNavigationView.getOrCreateBadge(R.id.emailFragment).apply {
            isVisible = true
        } // for dot badge
    }

    private fun removeBadge(itemId: Int) {
        bottomNavigationView.removeBadge(itemId)
    }
}