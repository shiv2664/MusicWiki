package com.shivam.musicwiki.models.albums

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class AlbumDataClass (

    @SerializedName("name"   ) var name   : String?= null,
    @SerializedName("mbid"   ) var mbid   : String?= null,
    @SerializedName("url"    ) var url    : String?= null,
    @SerializedName("artist" ) var artist : AlbumArtist?= AlbumArtist(),
    @SerializedName("image"  ) var image  : ArrayList<AlbumImage> = arrayListOf(),
//    @SerializedName("@attr"  ) var @attr  : @attr? = @attr()

)
