package com.escorp.movieworld.ui.detailScreen.actor.photos

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import javax.inject.Inject

class ActorPhotoViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getPhotos(personId: Int) = dataRepository.getPersonPhotos(personId)
}