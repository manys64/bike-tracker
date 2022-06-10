package com.example.bikeTracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coctails.R

class TrackDetailFragment : Fragment() {
    private var trackId: Long = 0
    private var trackType = TrackType.SHORT
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    fun setTrack(id: Long, type: TrackType) {
        trackId = id
        trackType = type
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("trackId", trackId)
        outState.putString("type", trackType.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            trackId = savedInstanceState.getLong("trackId")
            trackType =
                when (savedInstanceState.getString("type")) {
                    "LONG" -> TrackType.LONG
                    "SHORT" -> TrackType.SHORT
                    else -> TrackType.SHORT
                }

        }
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val track = Track.tracks.filter { t -> t.type == trackType }[trackId.toInt()]
            (view.findViewById<View>(R.id.textTitle) as TextView).text = track.name
            (view.findViewById<View>(R.id.trackDescription) as TextView).text = track.description
            (view.findViewById<View>(R.id.trackLength) as TextView).text = track.length.toString().plus(" km")
            (view.findViewById<View>(R.id.topTime) as TextView).text = track.topTime
        }
    }
}