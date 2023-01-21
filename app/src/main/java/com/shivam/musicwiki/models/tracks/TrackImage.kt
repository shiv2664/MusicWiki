package com.shivam.musicwiki.models.tracks

import com.google.gson.annotations.SerializedName


data class TrackImage (

    @SerializedName("#text" ) var text : String? = null,
    @SerializedName("size"  ) var size  : String? = null

)