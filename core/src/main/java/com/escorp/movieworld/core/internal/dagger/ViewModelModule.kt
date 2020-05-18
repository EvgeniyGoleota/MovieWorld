package com.escorp.movieworld.core.internal.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.core.dagger.key.ViewModelKey
import com.escorp.movieworld.core.internal.dagger.factory.ViewModelFactory
import com.escorp.movieworld.core.ui.feature.recoverable.RecoverableViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RecoverableViewModel::class)
    fun bindRecoverableViewModel(recaverableViewModel: RecoverableViewModel): ViewModel
}