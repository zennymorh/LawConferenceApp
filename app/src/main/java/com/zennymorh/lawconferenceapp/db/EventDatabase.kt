
package com.zennymorh.lawconferenceapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zennymorh.lawconferenceapp.models.Event


@Database(entities = [Event::class], version = 1, exportSchema = false)
@TypeConverters(AttendeesConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract val eventDao: EventDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EventDatabase::class.java,
                        "event_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
