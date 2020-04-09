package com.escorp.movieworld.actors.data

import com.escorp.movieworld.network.api.ActorsApiClient
import com.escorp.movieworld.network.api.dto.actors.APhotoResponse
import com.escorp.movieworld.network.api.dto.actors.Actor
import com.escorp.movieworld.network.api.dto.actors.ActorDetail
import com.escorp.movieworld.network.api.dto.common.CreditsResponse
import com.escorp.movieworld.network.api.dto.common.Response
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

interface ActorRepository {
    fun getPersonCredits(personId: Int): Single<CreditsResponse<Movie>>
    fun getPersonDetails(personId: Int): Single<ActorDetail>
    fun getPersonPhotos(personId: Int): Single<APhotoResponse>
    fun getPopularPeople(page: Int): Single<Response<Actor>>
}

internal class ActorRepositoryImpl @Inject constructor(
    private val actorsApiClient: ActorsApiClient
) : ActorRepository {
    override fun getPersonCredits(personId: Int): Single<CreditsResponse<Movie>> =
        actorsApiClient.getPersonsCombinedCredits(personId)

    override fun getPersonDetails(personId: Int): Single<ActorDetail> =
        actorsApiClient.getPersonDetail(personId)

    override fun getPersonPhotos(personId: Int): Single<APhotoResponse> =
        actorsApiClient.getPersonPhotos(personId)

    override fun getPopularPeople(page: Int): Single<Response<Actor>> =
        actorsApiClient.getPopularPeople(page)

}