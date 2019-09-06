package com.escorp.movieworld.ui.ActorsScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.api.models.Actor
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    val pagedActorsListLiveData: LiveData<PagedList<Actor>> by lazy {
        dataRepository.getPagedActorListLiveData()
    }

    fun retrievePopularPeople(page: Int) = dataRepository.getPopularPeople(page)
}