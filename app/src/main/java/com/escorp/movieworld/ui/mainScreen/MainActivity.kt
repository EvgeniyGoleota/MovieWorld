package com.escorp.movieworld.ui.mainScreen

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.detailScreen.DetailActivity
import com.escorp.movieworld.ui.mainScreen.actorsList.ActorsListFragment
import com.escorp.movieworld.ui.mainScreen.moviesList.MoviesListFragment
import com.escorp.movieworld.utils.*
import com.escorp.movieworld.utils.enums.DetailActivityTag
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_actors -> {
                    loadFragment(ActorsListFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_movies -> {
                    loadFragment(MoviesListFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        loadFragment(ActorsListFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

    fun startDetailActivity(tag: DetailActivityTag, id: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(TAG, tag)
        intent.putExtra(ID, id)
        startActivity(intent)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
