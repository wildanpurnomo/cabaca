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

    init {
        setLatestBook()
    }
}