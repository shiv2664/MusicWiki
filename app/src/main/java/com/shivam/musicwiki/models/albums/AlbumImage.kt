package com.shivam.musicwiki.models.albums

import com.google.gson.annotations.SerializedName


data class AlbumImage (

    @SerializedName("fulltrack") var fulltrack : String? = null,
    @SerializedName("#text" ) var text : String? = null,
    @SerializedName("size"  ) var size  : String? = null

)