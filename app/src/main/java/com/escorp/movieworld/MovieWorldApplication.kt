package com.escorp.movieworld

import com.escorp.movieworld.network.dagger.DaggerDataComponent
import com.escorp.movieworld.network.dagger.DataComponent
import com.escorp.movieworld.di.components.AppComponent
import com.escorp.movieworld.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MovieWorldApplication : DaggerApplication(){

    private lateinit var dataComponent: DataComponent
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        setupComponents()
        super.onCreate()
    }

    private fun setupComponents() {
        setupDataComponent()
        setupAppComponent()
    }

    private fun setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .dataComponent(dataComponent)
            .build()
    }

    private fun setupDataComponent() {
        dataComponent = DaggerDataComponent.builder()
            .bindContext(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? = appComponent
}