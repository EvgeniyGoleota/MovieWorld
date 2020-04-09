package com.escorp.movieworld.core.dagger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.core.ui.feature.recoverable.RecoverableViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CoreViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelMapKey(RecoverableViewModel::class)
    fun bindRecoverableViewModel(recaverableViewModel: RecoverableViewModel): ViewModel
}