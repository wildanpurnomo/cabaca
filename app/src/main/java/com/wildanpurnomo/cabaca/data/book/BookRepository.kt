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

    suspend fun getBookByGenreId(genreId: Int): BookAPIResponse? {
        return safeAPICall(
            call = { APIMain.services.getBookByGenreId(genreId) },
            errorMessage = "Error get book by genre"
        )
    }

    suspend fun getBookDetailById(bookId: Int): BookDetailAPIResponse? {
        return safeAPICall(
            call = { APIMain.services.getBookDetailById(bookId) },
            errorMessage = "Error get book detail"
        )
    }
}