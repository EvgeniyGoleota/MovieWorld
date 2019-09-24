package com.escorp.movieworld.ui.mainScreen

import com.escorp.movieworld.R
import com.escorp.movieworld.ui.mainScreen.actorsList.ActorsListFragment
import com.escorp.movieworld.ui.mainScreen.moviesList.MoviesListFragment
import com.escorp.movieworld.utils.PagerAdapter
import com.escorp.movieworld.utils.BasePagerFragment

class MainScreenFragment : BasePagerFragment() {

    override fun createPagerAdapter() = PagerAdapter(childFragmentManager).apply {
        addTab(ActorsListFragment() to resources.getString(R.string.title_actors))
        addTab(MoviesListFragment() to resources.getString(R.string.title_movies))
    }
}