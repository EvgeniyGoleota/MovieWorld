package com.escorp.movieworld

import android.app.Application
import com.escorp.movieworld.dagger.Components.AppComponent
import com.escorp.movieworld.dagger.Components.DaggerAppComponent
import com.escorp.movieworld.dagger.modules.AppModule
import com.escorp.movieworld.dagger.modules.NetworkModule

class Application : Application() {

    companion object {
        private lateinit var appComponent: AppComponent
        fun getApplicationComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}