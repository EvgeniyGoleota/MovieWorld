package com.escorp.movieworld.core.dagger

import com.escorp.movieworld.core.internal.dagger.*
import com.escorp.movieworld.core.internal.dagger.FragmentFactoryModule
import com.escorp.movieworld.core.internal.dagger.NetworkModule
import com.escorp.movieworld.core.internal.dagger.UtilsModule
import dagger.Module

@Module(
    includes = [
        UtilsModule::class,
        FragmentFactoryModule::class,
        NetworkModule::class,
        ApiModule::class,
        ViewModelModule::class
    ]
)
interface CoreModule