package com.example.composerealstate.di

import com.example.composerealstate.common.Constants
import com.example.composerealstate.network.HouseApiService
import com.example.composerealstate.network.AuthInterceptor
import com.example.composerealstate.network.FakeHouseApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class provides the dependencies required for networking in an Android application
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the [HttpLoggingInterceptor] for logging the network requests and responses
     */
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    /**
     * Provides the [OkHttpClient] for making network requests adding the [AuthInterceptor] with
     * the API key
     */
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(Constants.API_KEY)).build()
    }

    /**
     * Provides the [Retrofit] instance for making network requests and base url
     */
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    /**
     * Provides the [HouseApiService] for making API requests to get data about houses.
     */
    @Provides
    fun provideHouseApiService(retrofit: Retrofit): HouseApiService {
       // return retrofit.create(HouseApiService::class.java)
        //fake implementation
        return FakeHouseApiService()
    }
}