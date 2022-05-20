package com.example.bikeTracker

import android.os.Bundle
import android.view.View
import android.widget.ImageView
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
        val track = Track.getTrack().filter { t -> t.type == trackType }[trackId]
        supportActionBar?.title = track.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (findViewById<View>(R.id.track_image) as ImageView)
            .setImageResource(track.getImageId(this))
    }

    fun onClickFAB(view: View) {
        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as TrackDetailFragment?
        frag?.toggleTimer()
    }

    companion object {
        const val EXTRA_TRACK_ID = "id"
        const val TRACK_TYPE = "type"
    }
}