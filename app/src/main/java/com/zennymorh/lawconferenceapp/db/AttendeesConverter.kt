package com.zennymorh.lawconferenceapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zennymorh.lawconferenceapp.models.Attendee

class AttendeesConverter {
    private val gson = Gson()

    @TypeConverter
    fun convertFromAttendees(attendees: List<Attendee>): String {
        return gson.toJson(attendees)
    }

    @TypeConverter
    fun convertToAttendees(attendeesString: String): List<Attendee> {
        return gson.fromJson(
            attendeesString,
            object : TypeToken<List<Attendee>>() {}.type
        )
    }
}