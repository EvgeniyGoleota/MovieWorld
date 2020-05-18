package com.escorp.movieworld.actors.dagger

import com.escorp.movieworld.actors.internal.dagger.FragmentModule
import com.escorp.movieworld.actors.internal.dagger.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        FragmentModule::class
    ]
)
interface ActorFeatureModule