package com.escorp.movieworld.core.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
internal class CoreUtilsModule {

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}