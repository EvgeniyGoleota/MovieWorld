package com.escorp.movieworld.core.internal.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
internal class UtilsModule {

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}