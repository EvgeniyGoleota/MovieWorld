package com.escorp.movieworld.ui.detailScreen.movieDetail

import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieCast.MovieCastFragment
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieInfo.MovieInfoFragment
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieSimilar.SimilarMoviesFragment
import com.escorp.movieworld.utils.PagerAdapter
import com.escorp.movieworld.utils.BasePagerFragment

class MovieDetailFragment : BasePagerFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun createPagerAdapter() = PagerAdapter(childFragmentManager).apply {
        addTab(MovieInfoFragment.newInstance(args.movieId) to resources.getString(R.string.title_info))
        addTab(MovieCastFragment.newInstance(args.movieId) to resources.getString(R.string.cast))
        addTab(SimilarMoviesFragment.newInstance(args.movieId) to resources.getString(R.string.similar))
    }
}