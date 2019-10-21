package com.escorp.movieworld.ui.mainScreen

import com.escorp.movieworld.R
import com.escorp.movieworld.ui.mainScreen.actors.ActorsListFragment
import com.escorp.movieworld.ui.mainScreen.movies.MoviesListFragment
import com.escorp.movieworld.ui.uiUtils.PagerAdapter
import com.escorp.movieworld.ui.uiUtils.BasePagerFragment

class MainScreenFragment : BasePagerFragment() {

    override fun createPagerAdapter() = PagerAdapter(
        childFragmentManager
    ).apply {
        addTab(ActorsListFragment() to resources.getString(R.string.title_actors))
        addTab(MoviesListFragment() to resources.getString(R.string.title_movies))
    }
}