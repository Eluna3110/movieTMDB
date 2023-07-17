package io.software.servicecoordinator.service

import io.software.servicecoordinator.domain.response.Movie
import io.software.servicecoordinator.domain.response.PersonProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI
{
    @GET("movie/popular")
    fun getListMoviePopular(@Query("api_key") api_key: String?,
                             @Query("language") language: String?,
                             @Query("page") page: Int?) : Call<Movie?>?

    @GET("movie/top_rated")
    fun getListMovieTopRater(@Query("api_key") api_key: String?,
                              @Query("language") language: String?,
                              @Query("page") page: Int?) : Call<Movie?>?
}