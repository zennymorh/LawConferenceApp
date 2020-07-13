package com.zennymorh.lawconferenceapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zennymorh.lawconferenceapp.models.Event

@Dao
interface EventDao {
    @Query("UPDATE event_table SET isFavourite = :isFavourite WHERE id = :eventId")
    suspend fun update(eventId: String, isFavourite: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(events: List<Event>)

    @Query("SELECT * from event_table ORDER BY startDate DESC")
    fun getEvents(): LiveData<List<Event>>

    @Query("SELECT * from event_table")
    fun getEventsNoLiveData(): List<Event>

    @Query("SELECT * from event_table WHERE isFavourite = 1")
    fun getFavourite(): LiveData<List<Event>>
}