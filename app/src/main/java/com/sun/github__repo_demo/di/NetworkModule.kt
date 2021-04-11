package com.sun.github__repo_demo.di

import android.util.Log
import com.google.gson.Gson
import com.sun.github__repo_demo.data.remote.ApiService
import com.sun.github__repo_demo.data.remote.BaseSourceApi
import com.sun.github__repo_demo.data.remote.GithubRepoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    @BaseSourceApi("logging")
    fun provideLogging(): Interceptor {
        return ApiService.createLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i("OkHttp", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    @BaseSourceApi("header")
    fun provideHeader(): Interceptor {
        return ApiService.createHeaderInterceptor()
    }

    @Provides
    @Singleton
    @BaseSourceApi("sample")
    fun provideSampleOkHttpClient(
        @BaseSourceApi("logging") logging: Interceptor,
        @BaseSourceApi("header") header: Interceptor
    ): OkHttpClient {
        return ApiService.createOkHttpClient(logging, header)
    }

    @Provides
    @Singleton
    @BaseSourceApi("sample")
    fun provideSampleRetrofit(
        @BaseSourceApi("sample") okHttpClient: OkHttpClient
    ): Retrofit {
        return ApiService.createRetrofit(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiService(@BaseSourceApi("sample") retrofit: Retrofit): GithubRepoApi {
        return retrofit.create(GithubRepoApi::class.java)
    }
}
