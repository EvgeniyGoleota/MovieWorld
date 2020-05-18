package com.escorp.movieworld.movies.internal.screen.details.ui

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.core.ui.base.BasePagerFragment
import com.escorp.movieworld.core.ui.PagerAdapter
import com.escorp.movieworld.movies.R
import com.escorp.movieworld.movies.internal.screen.details.ui.cast.MovieCastFragment
import com.escorp.movieworld.movies.internal.screen.details.ui.info.MovieInfoFragment
import com.escorp.movieworld.movies.internal.screen.details.ui.similar.SimilarMoviesFragment
import javax.inject.Inject

internal class MovieDetailFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : BasePagerFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun createPagerAdapter() = PagerAdapter(
        childFragmentManager
    ).apply {
        addTab(MovieInfoFragment.newInstance(args.movieId, viewModelFactory) to resources.getString(R.string.title_info))
        addTab(MovieCastFragment.newInstance(args.movieId, viewModelFactory) to resources.getString(R.string.title_cast))
        addTab(SimilarMoviesFragment.newInstance(args.movieId, viewModelFactory) to resources.getString(R.string.title_similar))
    }
}