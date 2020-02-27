package com.escorp.movieworld.di.components

import com.escorp.movieworld.MovieWorldApplication
import com.escorp.movieworld.network.dagger.DataComponent
import com.escorp.movieworld.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        ViewModule::class,
        DbModule::class,
        AndroidSupportInjectionModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<MovieWorldApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieWorldApplication): Builder

        fun dataComponent(dataComponent: DataComponent): Builder

        fun build(): AppComponent
    }
}