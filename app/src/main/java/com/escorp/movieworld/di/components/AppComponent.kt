package com.escorp.movieworld.di.components

import com.escorp.movieworld.Application
import com.escorp.movieworld.di.modules.*
import com.escorp.movieworld.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
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
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: Application)
}