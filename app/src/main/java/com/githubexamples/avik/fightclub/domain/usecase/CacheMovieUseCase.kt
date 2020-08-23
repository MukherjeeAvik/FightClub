package com.githubexamples.avik.fightclub.domain.usecase

import com.githubexamples.avik.fightclub.domain.UseCase
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheMovieUseCase @Inject constructor() : UseCase<List<MovieListItem>>() {


    private val cacheList = LinkedHashMap<String, MovieListItem>(16, .75f, true);
    override fun subscribeForData(vararg params: Any): Observable<List<MovieListItem>> {


        return Observable.create { emitter ->
            val listOfMovies = ArrayList<MovieListItem>()
            cacheList.forEach { (key, value) ->
                listOfMovies.add(value)
            }
            emitter.onNext(listOfMovies.takeLast(5).asReversed())
        }

    }

    fun storeToCache(movieItem: MovieListItem): Completable {

        return Completable.fromCallable {
            cacheList.put(movieItem.movieId, movieItem)
        }

    }

}