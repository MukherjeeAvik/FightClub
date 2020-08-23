package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.data.entities.MovieCastResponse
import io.reactivex.Single

interface GetMovieCastingApiSource {
    fun getMovieDetailsFromApi(movieId:String): Single<MovieCastResponse>
}