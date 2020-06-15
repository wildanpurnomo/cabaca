package com.wildanpurnomo.cabaca.data.genre

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreAPIResponse(
    @SerializedName("resource")
    @Expose
    val responseData: ArrayList<GenreModel>? = null
)