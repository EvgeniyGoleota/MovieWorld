package com.escorp.movieworld

import com.escorp.movieworld.dagger.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TestApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
//            .application(this)
            .build()
}