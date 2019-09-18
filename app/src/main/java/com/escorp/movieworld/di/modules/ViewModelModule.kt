package com.escorp.movieworld.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.di.annotation.ViewModelMapKey
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo.ActorInfoViewModel
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits.ActorCreditsViewModel
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos.ActorPhotoViewModel
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieCast.MovieCastViewModel
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieInfo.MovieInfoViewModel
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieSimilar.SimilarMoviesViewModel
import com.escorp.movieworld.ui.mainScreen.actorsList.ActorsListViewModel
import com.escorp.movieworld.ui.mainScreen.moviesList.MoviesListViewModel
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