package com.escorp.movieworld.core.dagger

import dagger.Component
import io.reactivex.disposables.CompositeDisposable

@Component(
    modules = [
        CoreUtilsModule::class
    ]
)
interface CoreComponent {

    fun providesCompositeDisposable(): CompositeDisposable

    @Component.Builder
    interface Builder {

        fun build(): CoreComponent
    }
}