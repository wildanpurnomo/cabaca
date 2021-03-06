package com.wildanpurnomo.cabaca.ui

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun determineTabText(position: Int): String {
        return when (position) {
            0 -> "Buku Terbaru"
            1 -> "Cari Genre"
            else -> "Favorit"
        }
    }
}