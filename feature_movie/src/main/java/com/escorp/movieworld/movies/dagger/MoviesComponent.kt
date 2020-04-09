package com.escorp.movieworld.movies.dagger

import androidx.fragment.app.FragmentFactory
import com.escorp.movieworld.core.dagger.CoreComponent
import com.escorp.movieworld.movies.data.DataModule
import com.escorp.movieworld.network.dagger.NetworkComponent
import dagger.Component
import javax.inject.Provider

@MovieScope
@Component(
    modules = [
        DataModule::class,
        ViewModelBindings::class,
        MovieFragmentModule::class
    ],
    dependencies = [
        NetworkComponent::class,
        CoreComponent::class
    ]
)
interface MoviesComponent {

    fun provideFragmentFactory(): Provider<FragmentFactory>

    @Component.Builder
    interface Builder {

        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): MoviesComponent
    }
}