package com.wildanpurnomo.cabaca.data.book

import com.wildanpurnomo.cabaca.api.APIMain
import com.wildanpurnomo.cabaca.api.BaseRemoteRepository

class BookRepository : BaseRemoteRepository() {
    suspend fun getLatestBook(): BookAPIResponse? {
        return safeAPICall(
            call = { APIMain.services.getLatestBook() },
            errorMessage = "Error get latest book"
        )
    }
}