package com.shivam.musicwiki.models.albums

import com.google.gson.annotations.SerializedName


data class Json2KotlinInfoAlbum (

    @SerializedName("album" ) var albumInfo : AlbumInfo? = AlbumInfo()

)
