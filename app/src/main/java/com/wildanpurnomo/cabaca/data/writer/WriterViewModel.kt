package com.wildanpurnomo.cabaca.data.writer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WriterViewModel : ViewModel() {
    private val mWriterRepository = WriterRepository()

    private val writerDetail: MutableLiveData<WriterModel> by lazy {
        MutableLiveData<WriterModel>()
    }

    fun setWriterDetail(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mWriterRepository.getWriterByUserId(userId)
                if (response != null) {
                    writerDetail.postValue(response.responseData)
                }
            } catch (e: Exception) {
                Log.wtf("Error book by genre", e.message.toString())
            }
        }
    }

    fun getWriterDetail(): LiveData<WriterModel> {
        return writerDetail
    }
}