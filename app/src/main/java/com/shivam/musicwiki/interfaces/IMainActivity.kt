package com.shivam.musicwiki.interfaces

import com.shivam.musicwiki.models.genre.Tag

interface IMainActivity {
    fun onGenreClick(tag: Tag)
    fun getAlbumInfo(albumName: String, artistName: String)
    fun getArtistInfo(artistName: String)
    fun getTrackInfo(trackName: String)
}