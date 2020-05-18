package com.escorp.movieworld.movies.dagger

import com.escorp.movieworld.movies.internal.dagger.FragmentModule
import com.escorp.movieworld.movies.internal.dagger.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        FragmentModule::class
    ]
)
interface MovieFeatureModule