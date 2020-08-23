package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.data.api.ApiService
import com.githubexamples.avik.fightclub.data.entities.MovieListResponse
import com.githubexamples.avik.fightclub.domain.GetSimilarMovieApiSource
import com.githubexamples.avik.fightclub.utils.KEY_API_KEY
import com.githubexamples.avik.fightclub.utils.KEY_LANGUAGE
import com.githubexamples.avik.fightclub.utils.VALUE_API_KEY
import com.githubexamples.avik.fightclub.utils.VALUE_LANGUAGE
import io.reactivex.Single

class GetSimilarMovieResponseImpl(private val apiService: ApiService) :
    GetSimilarMovieApiSource {

    override fun getSimilarMovies(movieId:String): Single<MovieListResponse> {

        val queryMap = mapOf(
            KEY_API_KEY to VALUE_API_KEY,
            KEY_LANGUAGE to VALUE_LANGUAGE
        )
        return apiService.getSimilarMovies(movieId,queryMap)
    }

}