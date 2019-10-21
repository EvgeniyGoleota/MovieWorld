package com.escorp.movieworld.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.di.annotation.ViewModelMapKey
import com.escorp.movieworld.ui.detailScreen.actor.credits.ActorCreditsViewModel
import com.escorp.movieworld.ui.detailScreen.actor.info.ActorInfoViewModel
import com.escorp.movieworld.ui.detailScreen.actor.photos.ActorPhotoViewModel
import com.escorp.movieworld.ui.detailScreen.movie.cast.MovieCastViewModel
import com.escorp.movieworld.ui.detailScreen.movie.info.MovieInfoViewModel
import com.escorp.movieworld.ui.detailScreen.movie.similar.SimilarMoviesViewModel
import com.escorp.movieworld.ui.mainScreen.actors.ActorsListViewModel
import com.escorp.movieworld.ui.mainScreen.movies.MoviesListViewModel
import com.escorp.movieworld.ui.uiUtils.ViewModelFactory
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
    @ViewModelMapKey(ActorInfoViewModel::class)
    protected abstract fun actorInfoViewModel(actorInfoViewModel: ActorInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelMapKey(ActorPhotoViewModel::class)
    protected abstract fun actorPhotoViewModel(actorPhotoViewModel: ActorPhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelMapKey(ActorCreditsViewModel::class)
    protected abstract fun actorCreditsViewModel(actorCreditsViewModel: ActorCreditsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelMapKey(MovieInfoViewModel::class)
    protected abstract fun movieInfoViewModel(movieInfoViewModel: MovieInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelMapKey(MovieCastViewModel::class)
    protected abstract fun movieCastViewModel(movieCastViewModel: MovieCastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelMapKey(SimilarMoviesViewModel::class)
    protected abstract fun similarMoviesViewModel(similarMoviesViewModel: SimilarMoviesViewModel): ViewModel
}