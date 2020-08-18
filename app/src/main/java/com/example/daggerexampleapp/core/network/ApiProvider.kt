package com.example.daggerexampleapp.core.network

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author s.buvaka
 */
interface ApiProvider {

    fun provide(): PixelsApi
}

class ApiProviderImpl @Inject constructor(private val retrofit: Retrofit) : ApiProvider {

    override fun provide(): PixelsApi = retrofit.create(PixelsApi::class.java)
}
