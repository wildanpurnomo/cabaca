package com.wildanpurnomo.cabaca.data.writer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WriterAPIResponse(
    @SerializedName("result")
    @Expose
    val responseData: WriterModel? = null
)