package com.wildanpurnomo.cabaca.ui.writerDetail

import androidx.lifecycle.ViewModel
import com.wildanpurnomo.cabaca.utils.Constants

class WriterDetailViewModel : ViewModel() {
    fun getPhotoUrl(url: String): String {
        return Constants.API.IMAGE_BASE_URL + "/" + url + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"
    }

}