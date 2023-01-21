package com.shivam.musicwiki.models.albums

import com.google.gson.annotations.SerializedName


data class AlbumArtist (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("mbid" ) var mbid : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)
