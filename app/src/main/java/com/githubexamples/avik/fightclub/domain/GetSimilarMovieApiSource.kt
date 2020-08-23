package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.data.entities.MovieListResponse
import io.reactivex.Single

interface  GetSimilarMovieApiSource{
    fun getSimilarMovies(movieId:String): Single<MovieListResponse>
}