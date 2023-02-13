package com.aymenjegham.framework.di.module

import android.content.Context
import android.util.Log
import com.aymenjegham.framework.api.EndpointInterceptor
import com.aymenjegham.framework.di.qualifier.PicassoHttpClient
import com.aymenjegham.framework.global.constants.CONNECT_TIMEOUT
import com.aymenjegham.framework.global.constants.READ_TIMEOUT
import com.aymenjegham.framework.global.constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(
            "orange_log",
            message
        )
    }.run {
        level = HttpLoggingInterceptor.Level.BODY
        this
    }

    @Provides
    @Singleton
    fun providesOkHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        endpointInterceptor: EndpointInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(endpointInterceptor)
            .build()


    @Provides
    @Singleton
    @PicassoHttpClient
    fun providesPicassoOkHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: EndpointInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .cache(cache)
            .build()

}