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
            isVisible = true
        } // for dot badge
        bottomNavigationView.getOrCreateBadge(R.id.emailFragment).apply {
            number = 5 // for number badge
            isVisible = true
        }
    }

    //As best practice if you need to temporarily hide the badge (e.g. until the next notification is received), by changing the visibility
    private fun temporaryRemovalOfBadge(itemId: Int) {
        val badgeDrawable = bottomNavigationView.getBadge(itemId)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun permanentRemovalOfBadge(itemId: Int) {
        bottomNavigationView.removeBadge(itemId) //Permanently removing of badge
    }

}