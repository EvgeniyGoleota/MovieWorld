package com.escorp.movieworld.ui.detailScreen.movie

import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.detailScreen.movie.cast.MovieCastFragment
import com.escorp.movieworld.ui.detailScreen.movie.info.MovieInfoFragment
import com.escorp.movieworld.ui.detailScreen.movie.similar.SimilarMoviesFragment
import com.escorp.movieworld.ui.uiUtils.PagerAdapter
import com.escorp.movieworld.ui.uiUtils.BasePagerFragment

class MovieDetailFragment : BasePagerFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun createPagerAdapter() = PagerAdapter(
        childFragmentManager
    ).apply {
        addTab(MovieInfoFragment.newInstance(args.movieId) to resources.getString(R.string.title_info))
        addTab(MovieCastFragment.newInstance(args.movieId) to resources.getString(R.string.cast))
        addTab(SimilarMoviesFragment.newInstance(args.movieId) to resources.getString(R.string.similar))
    }
}