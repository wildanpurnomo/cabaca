package com.wildanpurnomo.cabaca.data.writer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WriterModel(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("phone")
    @Expose
    val phone: String? = null,

    @SerializedName("deskripsi")
    @Expose
    val desc: String? = null,

    @SerializedName("photo_url")
    @Expose
    val photoURL: String? = null
)