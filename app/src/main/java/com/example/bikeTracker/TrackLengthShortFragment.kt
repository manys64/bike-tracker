package com.example.bikeTracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coctails.R

class TrackLengthShortFragment : Fragment() {
    private var listener: Listener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trackRecycler = inflater.inflate(R.layout.fragment_track_length_short, container, false) as RecyclerView
        val names = mutableListOf<String>()
        val images = mutableListOf<Int>()
        for (track in Track.getTrack()) {
            if (track.type == TrackType.SHORT) {
                names.add(track.name)
                images.add(track.getImageId(requireContext()))
            }
        }
        val obj = object : CaptionedImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent
                    .putExtra(DetailActivity.EXTRA_TRACK_ID, position)
                    .putExtra(DetailActivity.TRACK_TYPE, TrackType.SHORT)
                activity?.startActivity(intent)
            }
        }
        val adapter = CaptionedImagesAdapter(names, images, obj )
        trackRecycler.adapter = adapter
        trackRecycler.layoutManager = GridLayoutManager(activity, 2)
        return trackRecycler
    }

    internal interface Listener {
        fun itemClicked(id: Long, type: TrackType)
    }
}