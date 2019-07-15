package com.escorp.movieworld.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule(private val context: Context) {

    @Reusable
    @Provides
    fun providesContext() = context.applicationContext
}