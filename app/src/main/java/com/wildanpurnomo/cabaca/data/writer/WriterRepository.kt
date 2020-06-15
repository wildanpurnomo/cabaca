package com.wildanpurnomo.cabaca.data.writer

import com.wildanpurnomo.cabaca.api.APIMain
import com.wildanpurnomo.cabaca.api.BaseRemoteRepository
import com.wildanpurnomo.cabaca.data.book.BookDetailAPIResponse
import com.wildanpurnomo.cabaca.ui.writerDetail.WriterDetailFragment

class WriterRepository : BaseRemoteRepository() {
    suspend fun getWriterByUserId(userId: Int): WriterAPIResponse? {
        return safeAPICall(
            call = { APIMain.services.getWriterDetailByUserId(userId) },
            errorMessage = "Error get book detail"
        )
    }
}