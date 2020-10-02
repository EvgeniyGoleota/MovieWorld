package com.escorp.movieworld.actors.internal.router

import android.net.Uri
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.actors.internal.screen.details.ui.ActorDetailFragmentArgs
import com.escorp.movieworld.core.activityprovider.RxActivityProvider
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ActorRouter @Inject constructor(
    private val activityProvider: RxActivityProvider
) {

    fun actorDetails(personId: Int, personName: String): Completable {
        return activityProvider.submitNavigation {
            val args = ActorDetailFragmentArgs(personId, personName)
            navigate(R.id.actorDetailsFragment, args.toBundle())
        }
    }

    fun movieDetails(movieId: Int, movieName: String): Completable {
        return activityProvider.submitNavigation {
            val uri = Uri.parse("movieworld://movies/$movieId/$movieName")
            navigate(uri)
        }
    }
}