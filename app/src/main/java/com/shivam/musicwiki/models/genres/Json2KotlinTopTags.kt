package com.shivam.musicwiki.models.genres

import com.google.gson.annotations.SerializedName


data class Json2KotlinTopTags(

    @SerializedName("toptags") var toptags : TopTags? = TopTags()


)

//var movieList: List<TopTags>? = null