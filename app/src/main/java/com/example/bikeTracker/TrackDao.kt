package com.example.bikeTracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrackDao {
    @Query("SELECT * FROM track")
    fun getAll(): List<Track>

    @Query("UPDATE track SET lastTime = :lastTime, topTime = :topTime WHERE name = :name")
    fun updateByName(name: String, lastTime: String, topTime: String)

    @Insert
    fun insertAll(vararg users: Track)
}