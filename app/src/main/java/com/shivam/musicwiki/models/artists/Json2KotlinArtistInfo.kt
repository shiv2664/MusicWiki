package com.shivam.musicwiki.models.artists

import com.google.gson.annotations.SerializedName

data class Json2KotlinArtistInfo (

    @SerializedName("artist" ) var artistDataClass : ArtistsDataClass? = ArtistsDataClass()

)
