package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.data.entities.MovieListResponse
import io.reactivex.Single

interface  GetMovieListApiSource{
    fun getListOfMovies(PageSize:Int):Single<MovieListResponse>
}