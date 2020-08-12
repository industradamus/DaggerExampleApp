package com.example.daggerexampleapp.core.network

import com.example.daggerexampleapp.core.models.PixelsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author s.buvaka
 */
interface PixelsApi {

    @GET("search/?per_page=15&query=people")
    @Headers("Authorization: 563492ad6f9170000100000110380711935f47d1a68bc523780310e1")
    fun getImageList(@Query("page") page: Int): Observable<Response<PixelsResponse>>
}
