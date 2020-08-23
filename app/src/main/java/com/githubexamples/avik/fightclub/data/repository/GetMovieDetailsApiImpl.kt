package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.data.api.ApiService
import com.githubexamples.avik.fightclub.data.entities.MovieDetailsResponse
import com.githubexamples.avik.fightclub.domain.GetMovieDetailsApiSource
import com.githubexamples.avik.fightclub.utils.KEY_API_KEY
import com.githubexamples.avik.fightclub.utils.KEY_LANGUAGE
import com.githubexamples.avik.fightclub.utils.VALUE_API_KEY
import com.githubexamples.avik.fightclub.utils.VALUE_LANGUAGE
import io.reactivex.Single

class GetMovieDetailsApiImpl(private val apiService: ApiService) :
    GetMovieDetailsApiSource {



    override fun getMovieDetailsFromApi(movieId: String): Single<MovieDetailsResponse> {
        val queryMap = mapOf(
            KEY_API_KEY to VALUE_API_KEY,
            KEY_LANGUAGE to VALUE_LANGUAGE
        )
        return apiService.getDetailsOfMovie(movieId,queryMap)
    }
}