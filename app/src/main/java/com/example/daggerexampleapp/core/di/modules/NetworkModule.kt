package com.example.daggerexampleapp.core.di.modules

import com.example.daggerexampleapp.core.network.*
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
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
@Module
object NetworkModule {

    private const val TIMEOUT_IN_SECOND = 30L
    private const val HEADER_AUTHORIZATION = "Authorization"

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun provideApiDataProvider(): ApiDataProvider {
        return ApiDataProviderImpl()
    }

    @Provides
    fun provideApiProvider(retrofit: Retrofit): ApiProvider {
        return ApiProviderImpl(retrofit)
    }

    @Provides
    fun providePixelsApi(apiProvider: ApiProvider): PixelsApi {
        return apiProvider.provide()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    fun provideApiKeyInterceptor(apiDataProvider: ApiDataProvider): Interceptor =
        Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .addHeader(HEADER_AUTHORIZATION, apiDataProvider.provideApiKey())
                .url(url)
                .build()

            chain.proceed(newRequest)
        }

    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
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
}
