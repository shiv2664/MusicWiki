package com.shivam.musicwiki.models.tracks

import com.google.gson.annotations.SerializedName

data class TracksMain (

    @SerializedName("track" ) var track : ArrayList<TracksDataClass> = arrayListOf(),
//    @SerializedName("@attr" ) var @attr : @attr?           = @attr()

)
