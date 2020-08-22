package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.data.api.ApiService
import com.githubexamples.avik.fightclub.data.entities.MovieListResponse
import com.githubexamples.avik.fightclub.domain.GetMovieListApiSource
import com.githubexamples.avik.fightclub.utils.*
import io.reactivex.Single

class GetMovieListFromApiImpl(private val apiService: ApiService) :
    GetMovieListApiSource {

    override fun getListOfMovies(pageSize: Int): Single<MovieListResponse> {

        val queryMap = mapOf(
            KEY_API_KEY to VALUE_API_KEY,
            KEY_LANGUAGE to VALUE_LANGUAGE,
            KEY_PAGE to pageSize.toString()
        )
        return apiService.getListOfMovies(queryMap)
    }

}