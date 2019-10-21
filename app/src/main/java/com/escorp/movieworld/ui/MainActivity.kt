package com.escorp.movieworld.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.uiUtils.DiActivity
import dagger.android.AndroidInjection

class MainActivity : DiActivity() {

    private val navController: NavController by lazy { findNavController(R.id.main_content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
