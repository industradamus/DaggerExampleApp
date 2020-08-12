package com.example.daggerexampleapp.core.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author s.buvaka
 */
class PixelsProvider {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
    private val apiDataProvider: ApiDataProvider = ApiDataProviderImpl()
    private val retrofit: Retrofit = buildRetrofit(
            apiDataProvider,
            buildOkHttpClient(apiDataProvider),
            GsonConverterFactory.create(GsonBuilder().setLenient().create()),
            RxJava2CallAdapterFactory.create())
    private val apiProvider: ApiProvider = ApiProviderImpl(retrofit)

    val pixelsApi: PixelsApi = providePicsumApi(apiProvider)

    private fun providePicsumApi(provider: ApiProvider): PixelsApi = provider.provide()

    private fun buildOkHttpClient(apiDataProvider: ApiDataProvider): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(getApiKeyInterceptor(apiDataProvider.provideApiKey()))
                .addInterceptor(loggingInterceptor)
                .readTimeout(TIMEOUT_IN_SECOND.toLong(), TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_IN_SECOND.toLong(), TimeUnit.SECONDS)
                .build()
    }

    private fun buildRetrofit(
            apiDataProvider: ApiDataProvider,
            client: OkHttpClient,
            converterFactory: Converter.Factory,
            callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(apiDataProvider.provideBaseUrl())
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()
    }

    private fun getApiKeyInterceptor(apiKey: String) = Interceptor { chain ->
        val url = chain.request()
                .url
                .newBuilder()
                .build()

        val newRequest = chain.request()
                .newBuilder()
                .addHeader(HEADER_AUTHORIZATION, apiKey)
                .url(url)
                .build()

        chain.proceed(newRequest)
    }

    companion object {

        private const val TIMEOUT_IN_SECOND = 30
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}
