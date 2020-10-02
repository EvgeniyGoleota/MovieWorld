package com.escorp.movieworld.movies.internal.router

import android.net.Uri
import com.escorp.movieworld.core.activityprovider.RxActivityProvider
import com.escorp.movieworld.movies.R
import com.escorp.movieworld.movies.internal.screen.details.ui.MovieDetailFragmentArgs
import io.reactivex.Completable
import javax.inject.Inject

class MovieRouter @Inject constructor(
    private val activityProvider: RxActivityProvider
) {

    fun movieDetails(movieId: Int, movieName: String): Completable {
        return activityProvider.submitNavigation {
            val args = MovieDetailFragmentArgs(movieId, movieName)
            navigate(R.id.movieDetailFragment, args.toBundle())
        }
    }

    fun actorDetails(actorId: Int, actorName: String): Completable {
        return activityProvider.submitNavigation {
            val uri = Uri.parse("movieworld://actors/$actorId/$actorName")
            navigate(uri)
        }
    }
}