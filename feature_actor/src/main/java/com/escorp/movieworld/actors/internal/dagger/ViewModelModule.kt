package com.escorp.movieworld.actors.internal.dagger

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.actors.internal.screen.details.ui.credits.ActorCreditsViewModel
import com.escorp.movieworld.actors.internal.screen.details.ui.info.ActorInfoViewModel
import com.escorp.movieworld.actors.internal.screen.details.ui.photos.ActorPhotoViewModel
import com.escorp.movieworld.actors.internal.screen.list.ui.ActorsListViewModel
import com.escorp.movieworld.core.dagger.key.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ViewModelModule {

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelKey(ActorsListViewModel::class)
    fun actorsListViewModel(actorsListViewModel: ActorsListViewModel): ViewModel

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelKey(ActorInfoViewModel::class)
    fun actorInfoViewModel(actorInfoViewModel: ActorInfoViewModel): ViewModel

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelKey(ActorPhotoViewModel::class)
    fun actorPhotoViewModel(actorPhotoViewModel: ActorPhotoViewModel): ViewModel

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelKey(ActorCreditsViewModel::class)
    fun actorCreditsViewModel(actorCreditsViewModel: ActorCreditsViewModel): ViewModel
}