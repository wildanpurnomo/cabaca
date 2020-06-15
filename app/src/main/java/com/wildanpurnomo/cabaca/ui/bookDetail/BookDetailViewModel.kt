package com.wildanpurnomo.cabaca.ui.bookDetail

import androidx.lifecycle.ViewModel
import com.wildanpurnomo.cabaca.data.genre.GenreModel
import com.wildanpurnomo.cabaca.utils.Constants

class BookDetailViewModel : ViewModel() {
    fun getImageUrl(coverURL: String): String {
        return Constants.API.IMAGE_BASE_URL + "/" + coverURL + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
    }

    fun getGenreString(genre: ArrayList<GenreModel>?): String {
        return if (!genre.isNullOrEmpty()) {
            var emptyString = ""
            for (i in 0 until genre.size) {
                emptyString += genre[i].genreName

                if (i != genre.size - 1) emptyString += ", "
            }
            emptyString
        } else {
            ""
        }
    }
}