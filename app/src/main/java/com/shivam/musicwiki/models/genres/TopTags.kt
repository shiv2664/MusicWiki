package com.shivam.musicwiki.models.genres

import com.google.gson.annotations.SerializedName
import com.shivam.musicwiki.models.genre.Tag


data class TopTags (

    @SerializedName("@attr") var Attr : Attr?= Attr(),
    @SerializedName("tag") var tag   : ArrayList<Tag> = arrayListOf()

)
