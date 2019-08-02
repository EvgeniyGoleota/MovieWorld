package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.adapters.ActorsListAdapter
import dagger.Module
import dagger.Provides

@Module
class ViewModule {

    @Provides
    fun providesActorsListAdapter(): ActorsListAdapter = ActorsListAdapter()
}