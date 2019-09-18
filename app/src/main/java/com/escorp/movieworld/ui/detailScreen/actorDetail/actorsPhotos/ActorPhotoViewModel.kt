package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import javax.inject.Inject

class ActorPhotoViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getPhotos(personId: Int) = dataRepository.getPersonPhotos(personId)
}