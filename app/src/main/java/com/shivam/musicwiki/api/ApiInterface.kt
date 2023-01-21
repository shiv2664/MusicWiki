package com.shivam.musicwiki.api

import com.shivam.musicwiki.models.albums.Json2KotlinAlbums
import com.shivam.musicwiki.models.albums.Json2KotlinInfoAlbum
import com.shivam.musicwiki.models.artists.Json2KotlinArtistInfo
import com.shivam.musicwiki.models.artists.Json2KotlinArtists
import com.shivam.musicwiki.models.genres.Json2KotlinTopTags
import com.shivam.musicwiki.models.tracks.Json2KotlinTracks
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("2.0/")
    fun getTopGenres(@Query("method") method: String,
                     @Query("api_key") api_key: String,
                     @Query("format") format: String): Call<Json2KotlinTopTags?>?

//   ?method=tag.gettopalbums&tag=disco&api_key=21efd60691998d773ccc2693a9ddce78&format=json
    @GET("2.0/")
    fun getTopAlbums(@Query("method") method: String,
                     @Query("tag") tag:String,
                     @Query("api_key") api_key: String,
                     @Query("format") format: String): Call<Json2KotlinAlbums?>?

//?method=tag.gettopartists&tag=disco&api_key=21efd60691998d773ccc2693a9ddce78&format=json
    @GET("2.0/")
    fun getTopArtists(@Query("method") method: String,
                 @Query("tag") tag:String,
                 @Query("api_key") api_key: String,
                 @Query("format") format: String): Call<Json2KotlinArtists?>?

//?method=tag.gettoptracks&tag=disco&api_key=21efd60691998d773ccc2693a9ddce78&format=json
    @GET("2.0/")
    fun getTopTracks(@Query("method") method: String,
                  @Query("tag") tag:String,
                  @Query("api_key") api_key: String,
                  @Query("format") format: String): Call<Json2KotlinTracks?>?

//method=album.getinfo&api_key=21efd60691998d773ccc2693a9ddce78&artist=Cher&album=Believe&format=json
    @GET("2.0/")
    fun getAlbumInfo(@Query("method") method: String,
                     @Query("api_key") api_key: String,
                     @Query("artist") artist:String,
                     @Query("album") album:String,
                     @Query("format") format: String): Call<Json2KotlinInfoAlbum?>?

    //method=artist.getinfo&artist=Cher&api_key=21efd60691998d773ccc2693a9ddce78&format=json

    @GET("2.0/")
    fun getArtistInfo(@Query("method") method: String,
                      @Query("artist") artist:String,
                      @Query("api_key") api_key: String,
                      @Query("format") format: String): Call<Json2KotlinArtistInfo?>?
}
