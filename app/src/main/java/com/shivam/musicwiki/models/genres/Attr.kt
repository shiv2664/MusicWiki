package com.shivam.musicwiki.models.genres

import com.google.gson.annotations.SerializedName

data class Attr (

@SerializedName("offset") var offset : Int? = null,
@SerializedName("num_res") var numRes : Int? = null,
@SerializedName("total") var total  : Int? = null

)
