package com.shivam.musicwiki.view_pager_adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shivam.musicwiki.GenreDetailsActivity
import com.shivam.musicwiki.databinding.ActivityMainBinding
import com.shivam.musicwiki.fragemnts.Albums
import com.shivam.musicwiki.fragemnts.Artists
import com.shivam.musicwiki.fragemnts.Tracks

class SamplePagerAdapter(activity: GenreDetailsActivity) : FragmentStateAdapter(activity) {



    override fun getItemCount(): Int  = 3

    override fun createFragment(position: Int): Fragment {

        return if (position == 0) {
            Albums()
        } else if (position == 1) {
            Artists()
        } else {
            Tracks()
        }
    }
}