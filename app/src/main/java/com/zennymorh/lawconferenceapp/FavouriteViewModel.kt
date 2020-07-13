package com.zennymorh.lawconferenceapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouriteViewModel() : ViewModel() {
    private val db = App.eventDatabase
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    val events: LiveData<List<Event>> = db.getFavourite()

    init {
    }

    fun updateEventFavourite(event: Event) {
        uiScope.launch {
            db.update(event.id, !event.isFavourite)
        }
    }
}