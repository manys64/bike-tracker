package com.example.bikeTracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coctails.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as TrackDetailFragment?
        val trackId = intent.extras!![EXTRA_TRACK_ID] as Int
        val trackType = intent.extras!![TRACK_TYPE] as TrackType
        frag!!.setTrack(trackId.toLong(), trackType)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = Track.getTrack().filter { t -> t.type == trackType }[trackId].name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val EXTRA_TRACK_ID = "id"
        const val TRACK_TYPE = "type"
    }
}