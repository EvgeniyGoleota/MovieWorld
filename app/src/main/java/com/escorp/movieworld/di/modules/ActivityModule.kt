package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.DetailActivity
import com.escorp.movieworld.ui.mainScreen.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contrebuteMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contrebuteDetailActivity(): DetailActivity
}