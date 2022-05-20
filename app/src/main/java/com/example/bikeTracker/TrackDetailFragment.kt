package com.example.bikeTracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coctails.R

class TrackDetailFragment : Fragment() {
    private var trackId: Long = 0
    private var trackType = TrackType.SHORT
    private var timerShow: Boolean = false
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.stoper_Container, StoperFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        } else {
            trackId = savedInstanceState.getLong("trackId")
        }
    }

    fun updateTimeInfo(lastTime: String, topTime: Boolean) {
        val view = view
        if (view != null) {
            if (topTime) {
                (view.findViewById<View>(R.id.topTime) as TextView).text = lastTime
            }
            (view.findViewById<View>(R.id.last_time) as TextView).text = lastTime
        }
    }

    fun toggleTimer() {
        val fragment = childFragmentManager.findFragmentById(R.id.stoper_Container) as StoperFragment?
        val fm = childFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
        if (fragment != null) {
            timerShow = if (timerShow) {
                fm.hide(fragment).commit()
                false
            } else {
                fm.show(fragment).commit()
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val track = Track.getTrack().filter { t -> t.type == trackType }[trackId.toInt()]
            (view.findViewById<View>(R.id.textTitle) as TextView).text = track.name
            (view.findViewById<View>(R.id.trackDescription) as TextView).text = track.description
            (view.findViewById<View>(R.id.trackLength) as TextView).text = track.length.toString().plus(" km")
            (view.findViewById<View>(R.id.topTime) as TextView).text = track.topTime
            (view.findViewById<View>(R.id.last_time) as TextView).text = track.lastTime


            val fragment = childFragmentManager.findFragmentById(R.id.stoper_Container) as StoperFragment?
            fragment?.setTrack(trackId, trackType)
        }
    }
}