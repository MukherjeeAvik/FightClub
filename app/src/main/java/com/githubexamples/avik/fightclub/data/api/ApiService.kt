package com.githubexamples.avik.fightclub.data.api


import com.githubexamples.avik.fightclub.data.entities.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("/3/movie/now_playing")
    fun getListOfMovies(
       @QueryMap  queryParams:Map<String,String>
    ): Single<MovieListResponse>

}