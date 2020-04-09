package com.escorp.movieworld.actors.dagger

import androidx.fragment.app.Fragment
import com.escorp.movieworld.actors.details.ui.ActorDetailFragment
import com.escorp.movieworld.actors.details.ui.credits.ActorCreditsFragment
import com.escorp.movieworld.actors.details.ui.info.ActorInfoFragment
import com.escorp.movieworld.actors.details.ui.photos.ActorPhotoFragment
import com.escorp.movieworld.actors.list.ui.ActorListFragment
import com.escorp.movieworld.core.dagger.fragment.DaggerFragmentModule
import com.escorp.movieworld.core.dagger.fragment.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [DaggerFragmentModule::class])
internal interface ActorFragmentModule {

    @ActorsScope
    @Binds
    @IntoMap
    @FragmentKey(ActorListFragment::class)
    fun bindActorListFragment(fragment: ActorListFragment): Fragment

    @ActorsScope
    @Binds
    @IntoMap
    @FragmentKey(ActorCreditsFragment::class)
    fun bindActorCreditsFragment(fragment: ActorCreditsFragment): Fragment

    @ActorsScope
    @Binds
    @IntoMap
    @FragmentKey(ActorInfoFragment::class)
    fun bindActorInfoFragment(fragment: ActorInfoFragment): Fragment

    @ActorsScope
    @Binds
    @IntoMap
    @FragmentKey(ActorPhotoFragment::class)
    fun bindActorPhotoFragment(fragment: ActorPhotoFragment): Fragment

    @ActorsScope
    @Binds
    @IntoMap
    @FragmentKey(ActorDetailFragment::class)
    fun bindActorDetailFragment(fragment: ActorDetailFragment): Fragment
}