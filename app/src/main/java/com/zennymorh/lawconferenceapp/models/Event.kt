package com.zennymorh.lawconferenceapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var star: Double,
    var isFavourite: Boolean,
    var overview: String,
    var picture: String,
    var title: String,
    var email: String,
    var phone: String,
    var addressNo: Int,
    var street: String,
    var city: String,
    var state: String,
    var country: String,
    var startDate: String,
    var endDate: String,
    var year: String,
    var attendees: List<Attendee>,
    var address: String
)

data class Attendee(
    var id: String,
    var name: String,
    var picture: String
)