package com.escorp.movieworld.actors.data

import com.escorp.movieworld.actors.dagger.ActorsScope
import dagger.Binds
import dagger.Module

@Module
internal interface DataModule {

    @Binds
    @ActorsScope
    fun bindsToActorRepository(impl: ActorRepositoryImpl): ActorRepository
}