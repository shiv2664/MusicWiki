package com.shivam.musicwiki.models.albums


import com.google.gson.annotations.SerializedName

data class Json2KotlinAlbums (

    @SerializedName("albums" ) val albumsDataMain : AlbumsDataMain? = AlbumsDataMain()
//    @SerializedName("@attr" ) var  attr : attr?           = attr()


)
