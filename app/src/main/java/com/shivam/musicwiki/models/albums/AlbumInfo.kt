package com.shivam.musicwiki.models.albums

import com.google.gson.annotations.SerializedName
import com.shivam.musicwiki.models.genres.TopTags


data class AlbumInfo (

    @SerializedName("artist"    ) var artist    : String?          = null,
    @SerializedName("mbid"      ) var mbid      : String?          = null,
    @SerializedName("tags"      ) var tags      : TopTags?            = TopTags(),
    @SerializedName("playcount" ) var playcount : String?          = null,
    @SerializedName("image"     ) var image     : ArrayList<AlbumImage> = arrayListOf(),
//    @SerializedName("tracks"    ) var tracks    : Tracks?          = Tracks(),
    @SerializedName("url"       ) var url       : String?          = null,
    @SerializedName("name"      ) var name      : String?          = null,
    @SerializedName("listeners" ) var listeners : String?          = null,
    @SerializedName("wiki"      ) var wiki      : AlbumWiki?       = AlbumWiki()

)