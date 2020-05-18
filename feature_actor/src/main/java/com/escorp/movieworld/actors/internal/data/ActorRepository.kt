package com.escorp.movieworld.actors.internal.data

import com.escorp.movieworld.core.data.api.ActorsApiClient
import com.escorp.movieworld.core.data.api.dto.actors.APhotoResponse
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

internal class ActorRepository @Inject constructor(
    private val actorsApiClient: ActorsApiClient
) {
    fun getPersonCredits(personId: Int): Single<CreditsResponse<Movie>> =
        actorsApiClient.getPersonsCombinedCredits(personId)

    fun getPersonDetails(personId: Int): Single<ActorDetail> =
        actorsApiClient.getPersonDetail(personId)

    fun getPersonPhotos(personId: Int): Single<APhotoResponse> =
        actorsApiClient.getPersonPhotos(personId)

    fun getPopularPeople(page: Int): Single<Response<Actor>> =
        actorsApiClient.getPopularPeople(page)

}