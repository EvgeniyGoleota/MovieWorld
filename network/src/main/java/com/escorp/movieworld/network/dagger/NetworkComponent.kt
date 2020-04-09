package com.escorp.movieworld.network.dagger

import android.content.Context
import com.escorp.movieworld.network.api.ActorsApiClient
import com.escorp.movieworld.network.api.ApiModule
import com.escorp.movieworld.network.api.MoviesApiClient
import dagger.BindsInstance
import dagger.Component

@NetworkScope
@Component(
    modules = [
        NetworkModule::class,
        ApiModule::class
    ]
)
interface NetworkComponent {

    fun getActorApiClient(): ActorsApiClient

    fun getMoviesApiClient(): MoviesApiClient

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): NetworkComponent
    }
}