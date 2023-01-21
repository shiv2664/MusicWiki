package com.shivam.musicwiki.models.tracks

import com.google.gson.annotations.SerializedName


data class TrackArtist (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("mbid" ) var mbid : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)
