package com.zennymorh.lawconferenceapp

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zennymorh.lawconferenceapp.db.EventDao
import com.zennymorh.lawconferenceapp.db.EventDatabase
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.charset.Charset


class App: Application(){

    override fun onCreate() {
        super.onCreate()
        eventDatabase = EventDatabase.getInstance(this).eventDao.also {
            GlobalScope.launch {
                insertEventsData()
            }
        }
    }

    private suspend fun insertEventsData() {
        if (eventDatabase.getEventsNoLiveData().isEmpty()) {
            val events = fetchEvents()
            eventDatabase.insert(events)
        }
    }

    private suspend fun fetchEvents() = withContext(Dispatchers.IO) {
        val jsonFile = this@App.assets.open("events.json")
        val size = jsonFile.available()
        val buffer = ByteArray(size)
        jsonFile.read(buffer)
        jsonFile.close()
        val jsonString = String(buffer, Charset.defaultCharset())

        val type = object: TypeToken<List<Event>>(){}.type
        return@withContext Gson().fromJson<List<Event>>(jsonString, type)
    }

    companion object{
        lateinit var eventDatabase: EventDao
    }
}