package com.wildanpurnomo.cabaca.data.genre

import com.wildanpurnomo.cabaca.api.APIMain
import com.wildanpurnomo.cabaca.api.BaseRemoteRepository

class GenreRepository : BaseRemoteRepository() {
    suspend fun getAllGenre(): GenreAPIResponse? {
        return safeAPICall(
            call = { APIMain.services.getAllGenre() },
            errorMessage = "Error get all genre"
        )
    }
}