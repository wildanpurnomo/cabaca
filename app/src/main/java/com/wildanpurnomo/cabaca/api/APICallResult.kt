package com.wildanpurnomo.cabaca.api

import java.lang.Exception

sealed class APICallResult<out T: Any> {
    data class Success<out T: Any>(val data: T) : APICallResult<T>()
    data class Error(val exception: Exception) : APICallResult<Nothing>()
}