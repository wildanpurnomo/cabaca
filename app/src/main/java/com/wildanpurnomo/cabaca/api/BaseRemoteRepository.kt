package com.wildanpurnomo.cabaca.api

import android.util.Log
import retrofit2.Response

open class BaseRemoteRepository {
    suspend fun <T : Any> safeAPICall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: APICallResult<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is APICallResult.Success ->
                data = result.data
            is APICallResult.Error -> {
                Log.wtf("BaseNetworkRepository", errorMessage)
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): APICallResult<T> {
        val response = call.invoke()
        if (response.isSuccessful) return APICallResult.Success(response.body()!!)

        return APICallResult.Error(Exception("Error: $errorMessage"));
    }
}