package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Completable
import io.reactivex.Observable

interface MovieSearchCacheRepository {

     fun getFromCache(): Observable<List<MovieListItem>>
    fun storeToCache(movieItem: MovieListItem): Completable

}