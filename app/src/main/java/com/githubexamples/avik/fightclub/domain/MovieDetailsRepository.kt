package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.domain.entitity.MovieDetails
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Single

interface MovieDetailsRepository {
    fun getMovieDetails(movieId:String):Single<MovieDetails>
    fun getMovieReviews(movieId:String)
    fun getGetMovieCast(movieId: String):Single<List<MovieCast>>
    fun getRecommendedMovies(movieId:String): Single<List<MovieListItem>>
}