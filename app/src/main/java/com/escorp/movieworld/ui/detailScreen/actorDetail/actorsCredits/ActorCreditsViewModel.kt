package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import javax.inject.Inject

class ActorCreditsViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getPersonsCredits(personId: Int) = dataRepository.getPersonCredits(personId)
}