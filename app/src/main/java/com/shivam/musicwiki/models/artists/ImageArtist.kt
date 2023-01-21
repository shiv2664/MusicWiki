package com.shivam.musicwiki.models.artists

import com.google.gson.annotations.SerializedName


data class ImageArtist (

    @SerializedName("#text" ) var text : String? = null,
    @SerializedName("size"  ) var size  : String? = null

)