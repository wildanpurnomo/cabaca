package com.wildanpurnomo.cabaca.data.genre

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class GenreViewModel : ViewModel() {
    private val mGenreRepository = GenreRepository()

    private val genreObjectList: MutableLiveData<ArrayList<GenreModel>> by lazy {
        MutableLiveData<ArrayList<GenreModel>>()
    }

    private val genreNameList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    fun getGenreNameList(): LiveData<ArrayList<String>> {
        return genreNameList
    }

    fun setGenre() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mGenreRepository.getAllGenre()
                if (response != null) {
                    if (!response.responseData.isNullOrEmpty()) {
                        val names = ArrayList<String>()
                        names.add("Pilih Genre")
                        for (item in response.responseData.orEmpty()) {
                            names.add(item.genreName.toString())
                        }
                        genreNameList.postValue(names)
                        genreObjectList.postValue(response.responseData)
                    }
                }
            } catch (e: Exception) {
                Log.wtf("Error Genre", e.message.toString())
            }
        }
    }

    fun getGenreId(selectedItemPosition: Int): Int? {
        return genreObjectList.value?.get(selectedItemPosition + 1)?.id
    }

    init {
        setGenre()
    }
}