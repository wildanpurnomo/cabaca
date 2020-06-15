package com.wildanpurnomo.cabaca.data.book

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookAPIResponse(
    @SerializedName("result")
    @Expose
    val responseData: ArrayList<BookModel>? = null
)