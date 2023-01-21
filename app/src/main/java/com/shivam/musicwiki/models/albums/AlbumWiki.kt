package com.shivam.musicwiki.models.albums

import com.google.gson.annotations.SerializedName

data class AlbumWiki (

    @SerializedName("published" ) var published : String? = null,
    @SerializedName("summary"   ) var summary   : String? = null,
    @SerializedName("content"   ) var content   : String? = null

)