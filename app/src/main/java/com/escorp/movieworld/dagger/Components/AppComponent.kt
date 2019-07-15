package com.escorp.movieworld.dagger.Components

import com.escorp.movieworld.activities.MainActivity
import com.escorp.movieworld.dagger.modules.AppModule
import com.escorp.movieworld.dagger.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}