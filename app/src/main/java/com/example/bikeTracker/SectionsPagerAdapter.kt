package com.example.bikeTracker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coctails.R

class SectionsPagerAdapter
constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return MainActivity.applicationContext().resources.getText(R.string.info_tab)
            1 -> return MainActivity.applicationContext().resources.getText(R.string.short_track_tab)
            2 -> return MainActivity.applicationContext().resources.getText(R.string.long_track_tab)
        }
        return null
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainTabFragment()
            1 -> return TrackLengthShortFragment()
            2 -> return TrackLengthLongFragment()
        }
        return MainTabFragment()
    }
}