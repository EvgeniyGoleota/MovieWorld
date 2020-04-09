package com.escorp.movieworld

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import com.escorp.movieworld.actors.dagger.DaggerActorsComponent
import com.escorp.movieworld.core.dagger.CoreComponent
import com.escorp.movieworld.core.dagger.DaggerCoreComponent
import com.escorp.movieworld.core.dagger.fragment.DelegateFragmentFactory
import com.escorp.movieworld.network.dagger.NetworkComponent
import com.escorp.movieworld.movies.dagger.DaggerMoviesComponent
import com.escorp.movieworld.network.dagger.DaggerNetworkComponent

class MovieWorldApplication : Application() {

    private lateinit var networkComponent: NetworkComponent
    private lateinit var coreComponent: CoreComponent

    private lateinit var fragmentFactory: FragmentFactory

    override fun onCreate() {
        setupComponents()
        super.onCreate()
    }

    private fun setupComponents() {
        coreComponent = DaggerCoreComponent.builder().build()
        setupNetworkComponent()
        setupFragmentFactory()
    }

    private fun setupFragmentFactory() {
        fragmentFactory = buildFragmentFactory()
        registerActivityLifecycleCallbacks(OnActivityCreatedLifecycleCallbacks {
            (it as? FragmentActivity?)?.let { fragmentActivity ->
                fragmentActivity.supportFragmentManager.fragmentFactory = fragmentFactory
            }
        })
    }

    private fun buildFragmentFactory(): FragmentFactory =
        DelegateFragmentFactory(
            listOf(
                DaggerActorsComponent.builder()
                    .networkComponent(networkComponent)
                    .coreComponent(coreComponent)
                    .build()
                    .provideFragmentFactory(),

                DaggerMoviesComponent.builder()
                    .bindNetworkComponent(networkComponent)
                    .coreComponent(coreComponent)
                    .build()
                    .provideFragmentFactory()
            )
        )

    private fun setupNetworkComponent() {
        networkComponent = DaggerNetworkComponent.builder()
            .bindContext(this)
            .build()
    }
}