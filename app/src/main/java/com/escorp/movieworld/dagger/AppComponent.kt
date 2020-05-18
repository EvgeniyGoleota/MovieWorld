package com.escorp.movieworld.dagger

import com.escorp.movieworld.MovieWorldApplication
import com.escorp.movieworld.actors.dagger.ActorFeatureModule
import com.escorp.movieworld.core.dagger.CoreModule
import com.escorp.movieworld.movies.dagger.MovieFeatureModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CoreModule::class,
        ActorFeatureModule::class,
        MovieFeatureModule::class
    ]
)
interface AppComponent {
    fun inject(application: MovieWorldApplication)
}