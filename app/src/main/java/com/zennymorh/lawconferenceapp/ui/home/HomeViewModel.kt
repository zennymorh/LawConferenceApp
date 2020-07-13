package com.zennymorh.lawconferenceapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zennymorh.lawconferenceapp.App
import com.zennymorh.lawconferenceapp.db.EventDao
import com.zennymorh.lawconferenceapp.db.EventDatabase
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.coroutines.*

class HomeViewModel() : ViewModel() {

    private val db = App.eventDatabase
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    val events: LiveData<List<Event>> = db.getEvents()

    init {
    }

    fun updateEventFavourite(event: Event) {
        uiScope.launch {
            db.update(event.id, !event.isFavourite)
        }
    }
}
