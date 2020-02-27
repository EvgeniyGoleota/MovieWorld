package com.escorp.movieworld.di.modules

import com.escorp.movieworld.data.api.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}