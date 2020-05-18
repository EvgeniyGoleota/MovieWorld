package com.escorp.movieworld.core.internal.dagger

import com.escorp.movieworld.core.data.api.ActorsApiClient
import com.escorp.movieworld.core.data.api.MoviesApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun providesActorApiClient(retrofit: Retrofit) = retrofit.create(ActorsApiClient::class.java)

    @Provides
    fun providesMovieApiClient(retrofit: Retrofit) = retrofit.create(MoviesApiClient::class.java)
}