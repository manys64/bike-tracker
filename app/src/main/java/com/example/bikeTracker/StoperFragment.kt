package com.example.bikeTracker

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.coctails.R

class StoperFragment : Fragment(), View.OnClickListener {
    private var seconds: Int = 0
    private var running: Boolean = false
    private var wasRunning: Boolean = false
    private var track: Track? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_stoper, container, false)
        runStopWatch(layout)
        layout.findViewById<Button>(R.id.start_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.stop_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.reset_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.save_time_button).setOnClickListener(this)
        return layout
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    private fun onClickSave() {
        running = false
        val minutes = seconds / 60
        val secs = seconds % 60
        val time = String.format("%02d:%02d", minutes, secs)
        if (track != null) {
            track!!.lastTime = time
            val topTime = track!!.topTime.split(":")
            var isBetter = false
            if (topTime[0].toInt() > minutes || (topTime[0].toInt() == minutes && topTime[1].toInt() > secs)) {
                isBetter = true
            }

            val db = Room.databaseBuilder(
                MainActivity.applicationContext(),
                AppDatabase::class.java, "database"
            ).allowMainThreadQueries().build()
            if (isBetter) {
                db.trackDao().updateByName(track!!.name, time, time)
            } else {
                db.trackDao().updateByName(track!!.name, time, track!!.topTime)
            }
            (parentFragment as TrackDetailFragment).updateTimeInfo(time, isBetter)
        }
        seconds = 0

    }

    fun setTrack(id: Long, type: TrackType) {
        track = Track.getTrack().filter { t -> t.type == type }[id.toInt()]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    private fun runStopWatch(view: View) {
        val timeView = view.findViewById<View>(R.id.timeView) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.start_button -> onClickStart()
                R.id.stop_button -> onClickStop()
                R.id.reset_button -> onClickReset()
                R.id.save_time_button -> onClickSave()
            }
        }
    }

}
