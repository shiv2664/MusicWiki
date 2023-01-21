package com.shivam.musicwiki.models.artists

import com.google.gson.annotations.SerializedName

data class Stats (

    @SerializedName("listeners" ) var listeners : String? = null,
    @SerializedName("playcount" ) var playcount : String? = null

)
