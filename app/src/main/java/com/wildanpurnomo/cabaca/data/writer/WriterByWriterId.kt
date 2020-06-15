package com.wildanpurnomo.cabaca.data.writer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WriterByWriterId(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("user_id")
    @Expose
    val userId: Int? = null,

    @SerializedName("User_by_user_id")
    @Expose
    val userByUserId: UserByUserId? = null
)