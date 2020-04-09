package com.escorp.movieworld.actors.details.ui.photos

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.data.ActorRepository
import com.escorp.movieworld.actors.domain.usecase.GetPersonPhotosUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.network.api.dto.common.Image
import javax.inject.Inject

class ActorPhotoViewModel @Inject constructor(
    private val getPersonPhotosUseCase: GetPersonPhotosUseCase
) : ViewModelBase() {

    fun getPhotos(personId: Int): LiveData<List<Image>> = getPersonPhotosUseCase(personId)
        .subscribeIoObserveMain()
        .toObservable()
        .toRecoverable()
        .toAutoDisposableLiveData()

}