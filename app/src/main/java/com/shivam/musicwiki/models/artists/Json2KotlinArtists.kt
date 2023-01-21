package com.shivam.musicwiki.models.artists

import com.google.gson.annotations.SerializedName

data class Json2KotlinArtists (

    @SerializedName("topartists") var topartists : TopArtists? = TopArtists()

)
