package com.shivam.musicwiki.models.genre

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag (

    @SerializedName("name") val name  : String? = null,
    @SerializedName("url"  ) var url  : String? = null,
    @SerializedName("count") val count : Int?    = null,
    @SerializedName("reach") val reach : Int?    = null
) :Parcelable {
    override fun equals(other: Any?): Boolean {

        if (javaClass != other?.javaClass) {
            return false
        }

        other as Tag

        if (name != other.name) {
            return false
        }
        if (count != other.count) {
            return false
        }
        if (reach   != other.reach  ) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (count ?: 0)
        result = 31 * result + (reach ?: 0)
        return result
    }

}

