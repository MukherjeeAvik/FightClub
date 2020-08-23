package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.data.entities.MovieDetailsResponse
import io.reactivex.Single

interface GetMovieDetailsApiSource {
 fun getMovieDetailsFromApi(movieId:String): Single<MovieDetailsResponse>
}