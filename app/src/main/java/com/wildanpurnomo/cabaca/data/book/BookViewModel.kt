package com.wildanpurnomo.cabaca.data.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class BookViewModel : ViewModel() {
    private val mBookRepository = BookRepository()

    private val isError: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getIsError(): LiveData<Boolean> {
        return isError
    }

    private val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getErrorMessage(): LiveData<String> {
        return errorMessage
    }

    private val latestBook: MutableLiveData<ArrayList<BookModel>> by lazy {
        MutableLiveData<ArrayList<BookModel>>()
    }

    fun setLatestBook() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mBookRepository.getLatestBook()
                if (response != null) {
                    if (!response.responseData.isNullOrEmpty()) {
                        latestBook.postValue(response.responseData)
                        isError.postValue(false)
                    } else {
                        isError.postValue(true)
                        errorMessage.postValue("Tidak ada data untuk ditampilkan.")
                    }
                }
            } catch (e: Exception) {
                isError.postValue(true)
                errorMessage.postValue("Gagal mendapatkan data.")
            }
        }
    }

    fun getLatestBook(): LiveData<ArrayList<BookModel>> {
        return latestBook
    }

    private val bookByGenre: MutableLiveData<ArrayList<BookModel>> by lazy {
        MutableLiveData<ArrayList<BookModel>>()
    }

    fun getBookByGenre(): LiveData<ArrayList<BookModel>> {
        return bookByGenre
    }

    fun setBookByGenre(genreId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mBookRepository.getBookByGenreId(genreId)
                if (response != null) {
                    if (!response.responseData.isNullOrEmpty()) {
                        bookByGenre.postValue(response.responseData)
                    }
                }
            } catch (e: Exception) {
                Log.wtf("Error book by genre", e.message.toString())
            }
        }
    }

    private val bookDetail: MutableLiveData<BookModel> by lazy {
        MutableLiveData<BookModel>()
    }

    fun setBookDetail(bookId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mBookRepository.getBookDetailById(bookId)
                if (response != null) {
                    bookDetail.postValue(response.responseData)
                }
            } catch (e: Exception) {
                Log.wtf("Error book by genre", e.message.toString())
            }
        }
    }

    fun getBookDetail(): LiveData<BookModel> {
        return bookDetail
    }

    init {
        setLatestBook()
    }
}