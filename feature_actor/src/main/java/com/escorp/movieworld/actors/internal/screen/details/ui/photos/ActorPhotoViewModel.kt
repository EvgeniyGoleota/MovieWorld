package com.escorp.movieworld.actors.internal.screen.details.ui.photos

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonPhotosUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.core.data.api.dto.common.Image
import javax.inject.Inject

internal class ActorPhotoViewModel @Inject constructor(
    private val getPersonPhotosUseCase: GetPersonPhotosUseCase
) : ViewModelBase() {

    fun getPhotos(personId: Int): LiveData<List<Image>> = getPersonPhotosUseCase(personId)
        .subscribeIoObserveMain()
        .toObservable()
        .toRecoverable()
        .toAutoDisposableLiveData()

}