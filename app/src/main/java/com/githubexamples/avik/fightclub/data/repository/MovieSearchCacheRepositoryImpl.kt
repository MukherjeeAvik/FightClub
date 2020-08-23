package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.domain.MovieSearchCacheRepository
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Completable
import io.reactivex.Observable

class MovieSearchCacheRepositoryImpl : MovieSearchCacheRepository {

    private val cacheList = LinkedHashMap<String, MovieListItem>(16, .75f, true)
    override fun getFromCache(): Observable<List<MovieListItem>> {

        return Observable.create { emitter ->
            val listOfMovies = ArrayList<MovieListItem>()
            cacheList.forEach { (key, value) ->
                listOfMovies.add(value)
            }
            emitter.onNext(listOfMovies.takeLast(5).asReversed())
        }
    }

    override fun storeToCache(movieItem: MovieListItem): Completable {
        return Completable.fromCallable {
            cacheList.put(movieItem.movieId, movieItem)
        }
    }
}