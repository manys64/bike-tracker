package com.example.bikeTracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Track::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun trackDao(): TrackDao
}