package com.wildanpurnomo.cabaca.data.genre

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("title")
    @Expose
    val genreName: String? = null
)