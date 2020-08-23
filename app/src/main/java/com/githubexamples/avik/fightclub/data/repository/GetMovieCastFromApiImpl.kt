package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.data.api.ApiService
import com.githubexamples.avik.fightclub.data.entities.MovieCastResponse
import com.githubexamples.avik.fightclub.domain.GetMovieCastingApiSource
import com.githubexamples.avik.fightclub.utils.KEY_API_KEY
import com.githubexamples.avik.fightclub.utils.KEY_LANGUAGE
import com.githubexamples.avik.fightclub.utils.VALUE_API_KEY
import com.githubexamples.avik.fightclub.utils.VALUE_LANGUAGE
import io.reactivex.Single

class GetMovieCastFromApiImpl(private val apiService: ApiService) :
    GetMovieCastingApiSource {


    override fun getMovieDetailsFromApi(movieId: String): Single<MovieCastResponse> {
        val queryMap = mapOf(
            KEY_API_KEY to VALUE_API_KEY,
            KEY_LANGUAGE to VALUE_LANGUAGE
        )
        return apiService.getCastOfMovie(movieId, queryMap)
    }
}