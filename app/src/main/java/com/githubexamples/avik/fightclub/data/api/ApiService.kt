package com.githubexamples.avik.fightclub.data.api


import com.githubexamples.avik.fightclub.data.entities.MovieCastResponse
import com.githubexamples.avik.fightclub.data.entities.MovieDetailsResponse
import com.githubexamples.avik.fightclub.data.entities.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/3/movie/now_playing")
    fun getListOfMovies(
        @QueryMap queryParams: Map<String, String>
    ): Single<MovieListResponse>

    @GET("/3/movie/{movie_id}")
    fun getDetailsOfMovie(
        @Path("movie_id") movieId: String,
        @QueryMap queryParams: Map<String, String>
    ) :Single<MovieDetailsResponse>

    @GET("/3/movie/{movie_id}/credits")
    fun getCastOfMovie(
        @Path("movie_id") movieId: String,
        @QueryMap queryParams: Map<String, String>
    ) :Single<MovieCastResponse>

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: String,
        @QueryMap queryParams: Map<String, String>
    ) :Single<MovieListResponse>


}