package com.escorp.movieworld.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.di.annotation.ViewModelMapKey
import com.escorp.movieworld.ui.viewmodel.ActorsListViewModel
import com.escorp.movieworld.ui.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelMapKey(ActorsListViewModel::class)
    protected abstract fun actorsListViewModel(actorsListViewModel: ActorsListViewModel): ViewModel
}