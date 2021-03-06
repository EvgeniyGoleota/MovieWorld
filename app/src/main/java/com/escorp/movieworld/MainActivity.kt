package com.escorp.movieworld

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.escorp.movieworld.core.router.NavigationControllerHost
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), NavigationControllerHost {

    override val navController: NavController by lazy { findNavController(R.id.main_content) }

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.navigation_view)

        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.navigation)
        graph.startDestination = R.id.actor_navigation
        navController.graph = graph

        bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.actorDetailsFragment -> {
                    hideBottomNavigation()
                }
                R.id.movieDetailFragment -> {
                    hideBottomNavigation()
                }
                else -> {
                    showBottomNavigation()
                }
            }
        }

        setupActionBarWithNavController(navController)
    }

    private fun setUpNewMenuForNavigationBar(menuId: Int) {
        bottomNavigation.apply {
            menu.clear()
            inflateMenu(menuId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun showBottomNavigation() {
        bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        bottomNavigation.visibility = View.GONE
    }
}
