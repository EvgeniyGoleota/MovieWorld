package com.escorp.movieworld.actors.dagger

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.actors.details.ui.credits.ActorCreditsViewModel
import com.escorp.movieworld.actors.details.ui.info.ActorInfoViewModel
import com.escorp.movieworld.actors.details.ui.photos.ActorPhotoViewModel
import com.escorp.movieworld.actors.list.ui.ActorsListViewModel
import com.escorp.movieworld.core.dagger.viewmodel.CoreViewModelModule
import com.escorp.movieworld.core.dagger.viewmodel.ViewModelMapKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [CoreViewModelModule::class])
internal interface ViewModelBindings {

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelMapKey(ActorsListViewModel::class)
    fun actorsListViewModel(actorsListViewModel: ActorsListViewModel): ViewModel

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelMapKey(ActorInfoViewModel::class)
    fun actorInfoViewModel(actorInfoViewModel: ActorInfoViewModel): ViewModel

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelMapKey(ActorPhotoViewModel::class)
    fun actorPhotoViewModel(actorPhotoViewModel: ActorPhotoViewModel): ViewModel

    @ActorsScope
    @Binds
    @IntoMap
    @ViewModelMapKey(ActorCreditsViewModel::class)
    fun actorCreditsViewModel(actorCreditsViewModel: ActorCreditsViewModel): ViewModel
}