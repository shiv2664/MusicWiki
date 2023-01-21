package com.shivam.musicwiki.models.albums

import com.google.gson.annotations.SerializedName


data class AlbumsDataMain (

    @SerializedName("album") val album : ArrayList<AlbumDataClass> = arrayListOf()
//    @SerializedName("@attr" ) var @attr : @attr?           = @attr()

)
