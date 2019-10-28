package com.escorp.movieworld.di.components

import com.escorp.movieworld.Application
import com.escorp.movieworld.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
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
    ]
)
@Singleton
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}