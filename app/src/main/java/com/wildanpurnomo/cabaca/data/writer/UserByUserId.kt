package com.wildanpurnomo.cabaca.data.writer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserByUserId (
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null
)