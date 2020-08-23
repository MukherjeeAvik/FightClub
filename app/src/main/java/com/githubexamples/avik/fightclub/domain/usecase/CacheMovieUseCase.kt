package com.githubexamples.avik.fightclub.domain.usecase

import com.githubexamples.avik.fightclub.domain.MovieSearchCacheRepository
import com.githubexamples.avik.fightclub.domain.UseCase
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

class CacheMovieUseCase @Inject constructor(private val moviesCacheRepo: MovieSearchCacheRepository) :
    UseCase<List<MovieListItem>>() {

    override fun subscribeForData(vararg params: Any): Observable<List<MovieListItem>> {
        return moviesCacheRepo.getFromCache()
    }

    fun storeToCache(movieItem: MovieListItem): Completable {

        return moviesCacheRepo.storeToCache(movieItem)
    }

}