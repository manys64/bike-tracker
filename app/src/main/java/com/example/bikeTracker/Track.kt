package com.example.bikeTracker

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Track private constructor(
    val name: String,
    val length: Float,
    val topTime: String,
    val description: String,
    val type: TrackType,
    val map: String?){
    override fun toString(): String {
        return name
    }

    companion object {
        private val jsonFileString = JsonFromAsset.getJsonDataFromAsset(MainActivity.applicationContext(), "tracks.json")
        private val gson = Gson()
        private val tracksType = object : TypeToken<List<Track>>() {}.type
        val tracks: List<Track> = gson.fromJson(jsonFileString, tracksType)
    }
}
