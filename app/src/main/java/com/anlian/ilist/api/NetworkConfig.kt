package com.anlian.ilist.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {
    val BASE_URL = "https://api.themoviedb.org/3/"
    //set interseptor address
    fun getInterseptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterseptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Service::class.java)
}