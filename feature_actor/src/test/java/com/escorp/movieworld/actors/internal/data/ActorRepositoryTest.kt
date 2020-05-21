package com.escorp.movieworld.actors.internal.data

import com.escorp.movieworld.core.data.api.ActorsApiClient
import com.escorp.movieworld.core.data.api.dto.actors.APhotoResponse
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ActorRepositoryTest {
    private val TEST_PERSON_ID = Int.MAX_VALUE
    private val TEST_CREDITS_RESPONSE = mock<CreditsResponse<Movie>>()
    private val TEST_DETAILS_RESPONSE = mock<ActorDetail>()
    private val TEST_PHOTO_RESPONSE = mock<APhotoResponse>()
    private val TEST_PEOPLE_RESPONSE = mock<Response<Actor>>()

    private val mockActorsApiClient = mock<ActorsApiClient>()

    private val actorRepository = ActorRepository(mockActorsApiClient)

    @Test
    fun getPersonCredits() {
        whenever(mockActorsApiClient.getPersonsCombinedCredits(any())).thenReturn(Single.just(TEST_CREDITS_RESPONSE))

        actorRepository.getPersonCredits(TEST_PERSON_ID).test().assertComplete()

        verify(mockActorsApiClient).getPersonsCombinedCredits(TEST_PERSON_ID)
        verifyNoMoreInteractions(mockActorsApiClient)
    }

    @Test
    fun getPersonDetails() {
        whenever(mockActorsApiClient.getPersonDetail(any())).thenReturn(Single.just(TEST_DETAILS_RESPONSE))

        actorRepository.getPersonDetails(TEST_PERSON_ID).test().assertComplete()

        verify(mockActorsApiClient).getPersonDetail(TEST_PERSON_ID)
        verifyNoMoreInteractions(mockActorsApiClient)
    }

    @Test
    fun getPersonPhotos() {
        whenever(mockActorsApiClient.getPersonPhotos(any())).thenReturn(Single.just(TEST_PHOTO_RESPONSE))

        actorRepository.getPersonPhotos(TEST_PERSON_ID).test().assertComplete()

        verify(mockActorsApiClient).getPersonPhotos(TEST_PERSON_ID)
        verifyNoMoreInteractions(mockActorsApiClient)
    }

    @Test
    fun getPopularPeople() {
        whenever(mockActorsApiClient.getPopularPeople(any())).thenReturn(Single.just(TEST_PEOPLE_RESPONSE))

        actorRepository.getPopularPeople(TEST_PERSON_ID).test().assertComplete()

        verify(mockActorsApiClient).getPopularPeople(TEST_PERSON_ID)
        verifyNoMoreInteractions(mockActorsApiClient)
    }
}