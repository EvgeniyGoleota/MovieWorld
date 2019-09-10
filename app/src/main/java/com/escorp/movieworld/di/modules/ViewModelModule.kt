package com.escorp.movieworld.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.di.annotation.ViewModelMapKey
import com.escorp.movieworld.ui.actorDetailScreen.ActorDetailViewModel
import com.escorp.movieworld.ui.actorsScreen.ActorsListViewModel
import com.escorp.movieworld.ui.moviesScreen.MoviesListViewModel
import com.escorp.movieworld.utils.ViewModelFactory
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

    @Binds
    @IntoMap
    @ViewModelMapKey(MoviesListViewModel::class)
    protected abstract fun moviesListViewModel(moviesListViewModel: MoviesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelMapKey(ActorDetailViewModel::class)
    protected abstract fun actorDetailViewModel(actorDetailViewModel: ActorDetailViewModel): ViewModel
}