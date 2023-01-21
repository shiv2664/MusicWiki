package com.shivam.musicwiki.models.artists
import com.google.gson.annotations.SerializedName


data class Bio (

//    @SerializedName("links"     ) var links     : Links?  = Links(),
    @SerializedName("published" ) var published : String? = null,
    @SerializedName("summary"   ) var summary   : String? = null,
    @SerializedName("content"   ) var content   : String? = null

)
