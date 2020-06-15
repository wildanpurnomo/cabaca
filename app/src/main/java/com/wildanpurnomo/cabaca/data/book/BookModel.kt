package com.wildanpurnomo.cabaca.data.book

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wildanpurnomo.cabaca.data.writer.WriterByWriterId

data class BookModel(
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("writer_id")
    @Expose
    val writerId: Int? = null,

    @SerializedName("cover_url")
    @Expose
    val coverUrl: String? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("category")
    @Expose
    val category: String? = null,

    @SerializedName("Writer_by_writer_id")
    @Expose
    val writerByWriterId: WriterByWriterId? = null,

    @SerializedName("is_update")
    @Expose
    val isUpdate: Boolean? = null,

    @SerializedName("book_id")
    @Expose
    val bookId: Int? = null,

    @SerializedName("isNew")
    @Expose
    val isNew: Boolean? = null,

    @SerializedName("view_count")
    @Expose
    val viewCount: Int? = null,

    @SerializedName("rate_sum")
    @Expose
    val rateCount: Float? = null,

    @SerializedName("chapter_count")
    @Expose
    val chapterCount: Int? = null
)