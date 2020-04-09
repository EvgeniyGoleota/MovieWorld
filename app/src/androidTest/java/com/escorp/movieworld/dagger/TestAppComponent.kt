package com.escorp.movieworld.dagger

import com.escorp.movieworld.dagger.modules.*
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        ViewModule::class,
        DatabaseModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface TestAppComponent : AppComponent {

}