package com.example.daggerexampleapp.core.network

import javax.inject.Inject

/**
 * @author s.buvaka
 */
interface ApiDataProvider {

    fun provideBaseUrl(): String

    fun provideApiKey(): String
}

class ApiDataProviderImpl @Inject constructor() : ApiDataProvider {

    override fun provideBaseUrl(): String = "https://api.pexels.com/v1/"

    override fun provideApiKey(): String =
        "563492ad6f9170000100000110380711935f47d1a68bc523780310e1"
}
