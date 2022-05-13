package com.example.bikeTracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.coctails.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), TrackLengthShortFragment.Listener,
    TrackLengthLongFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = SectionsPagerAdapter(supportFragmentManager)
        findViewById<TabLayout>(R.id.tabs).setupWithViewPager(pager)
    }

    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun itemClicked(id: Long, type: TrackType) {
        val intent = Intent(this, DetailActivity::class.java)
        intent
            .putExtra(DetailActivity.EXTRA_TRACK_ID, id.toInt())
            .putExtra(DetailActivity.TRACK_TYPE, type)
        startActivity(intent)
    }
}