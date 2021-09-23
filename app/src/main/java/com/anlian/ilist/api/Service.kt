package com.anlian.ilist.api

import com.anlian.ilist.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("movie/popular")
    fun getPopularFilm(@Suppress("api_key") apiKey:String?): Call <MovieResponse>
    @GET("movie/popular?api_key=9b0be6c15ab706d1a34253ea1f223df9")
    fun getPopularMovie(): Call<MovieResponse>
}