package com.shivam.musicwiki.models.tracks


import com.google.gson.annotations.SerializedName


data class Json2KotlinTracks (

    @SerializedName("tracks" ) var tracksMain : TracksMain? = TracksMain()

)