package com.shivam.musicwiki.models.artists

import com.google.gson.annotations.SerializedName


data class TopArtists (

    @SerializedName("artist" ) var artist : ArrayList<ArtistsDataClass> = arrayListOf()
//    @SerializedName("@attr"  ) var @attr  : @attr?            = @attr()

)
