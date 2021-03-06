package com.wildanpurnomo.cabaca.api

import com.wildanpurnomo.cabaca.data.book.BookAPIResponse
import com.wildanpurnomo.cabaca.data.book.BookDetailAPIResponse
import com.wildanpurnomo.cabaca.data.genre.GenreAPIResponse
import com.wildanpurnomo.cabaca.data.writer.WriterAPIResponse
import com.wildanpurnomo.cabaca.utils.Constants.API.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("book/uptodate")
    suspend fun getLatestBook(
        @Query("limit") limit: Int = 7,
        @Header("x-dreamfactory-api-key") apiKey: String = API_KEY
    ): Response<BookAPIResponse>

    @GET("book/category")
    suspend fun getBookByGenreId(
        @Query("id") genreId: Int? = null,
        @Header("x-dreamfactory-api-key") apiKey: String = API_KEY
    ): Response<BookAPIResponse>

    @GET("book/detail/{book_id}")
    suspend fun getBookDetailById(
        @Path("book_id") bookId: Int? = null,
        @Header("x-dreamfactory-api-key") apiKey: String = API_KEY
    ): Response<BookDetailAPIResponse>

    @GET("cabaca/_table/genre")
    suspend fun getAllGenre(
        @Header("x-dreamfactory-api-key") apiKey: String = API_KEY
    ): Response<GenreAPIResponse>

    @GET("writer/detail/{user_id}")
    suspend fun getWriterDetailByUserId(
        @Path("user_id") userId: Int? = null,
        @Header("x-dreamfactory-api-key") apiKey: String = API_KEY
    ): Response<WriterAPIResponse>
}