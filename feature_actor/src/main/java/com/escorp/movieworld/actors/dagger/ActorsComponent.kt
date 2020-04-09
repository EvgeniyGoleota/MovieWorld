package com.escorp.movieworld.actors.dagger

import androidx.fragment.app.FragmentFactory
import com.escorp.movieworld.actors.data.DataModule
import com.escorp.movieworld.core.dagger.CoreComponent
import com.escorp.movieworld.network.dagger.NetworkComponent
import dagger.Component
import javax.inject.Provider


@ActorsScope
@Component(
    modules = [
        DataModule::class,
        ViewModelBindings::class,
        ActorFragmentModule::class
    ],
    dependencies = [
        NetworkComponent::class,
        CoreComponent::class
    ]
)
interface ActorsComponent {

    fun provideFragmentFactory(): Provider<FragmentFactory>

    @Component.Builder
    interface Builder {

        fun networkComponent(networkComponent: NetworkComponent): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): ActorsComponent
    }
}