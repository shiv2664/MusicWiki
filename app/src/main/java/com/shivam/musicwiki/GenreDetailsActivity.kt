package com.shivam.musicwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.shivam.musicwiki.databinding.ActivityGenreDetailsBinding
import com.shivam.musicwiki.interfaces.IMainActivity
import com.shivam.musicwiki.models.genre.Tag
import com.shivam.musicwiki.view_pager_adapters.SamplePagerAdapter

class GenreDetailsActivity : AppCompatActivity(),IMainActivity {
    private lateinit var binding: ActivityGenreDetailsBinding
    lateinit var genreItem: Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_genre_details)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "musicWiki"

         genreItem= intent.getParcelableExtra<Tag>("genreItem")!!
         binding.genre.text=genreItem.name

        val demoCollectionPagerAdapter = SamplePagerAdapter(this)
        val viewPager = findViewById<ViewPager2>(R.id.vp_landing)
        viewPager.adapter = demoCollectionPagerAdapter

//        val tabLayout = findViewById<TabLayout>(R.id.tl_landing)
        val tabLayout=binding.tlLanding
//        TabLayoutMediator(tabLayout, viewPager) { tab, position -> tab.text = "LABEL ${(position + 1)}" }.attach()
        TabLayoutMediator(tabLayout, viewPager) {tab, position -> viewPager.setCurrentItem(tab.position, true)
            if(position == 0) tab.text = "ALBUMS"
            if(position == 1) tab.text=("ARTISTS")
            if(position==2) tab.text="TRACKS"}.attach()

    }

    public fun getGenre(): Tag {
        return genreItem
    }

    override fun onGenreClick(tag: Tag) {
        TODO("Not yet implemented")
    }

    override fun getAlbumInfo(albumName: String, artistName: String) {
        val intent: Intent = Intent(this@GenreDetailsActivity,InfoActivity::class.java)
        intent.putExtra("albumName", albumName)
        intent.putExtra("artistName", artistName)
        startActivity(intent)
    }

    override fun getArtistInfo(artistName: String) {

        val intent: Intent = Intent(this@GenreDetailsActivity, ArtistDetailsActivity::class.java)
        intent.putExtra("artistName", artistName)
        startActivity(intent)

    }

    override fun getTrackInfo(trackName: String) {

    }
}