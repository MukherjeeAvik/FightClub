package com.githubexamples.avik.fightclub.data.repository

import com.githubexamples.avik.fightclub.data.entities.ResultsItem
import com.githubexamples.avik.fightclub.domain.GetMovieListApiSource
import com.githubexamples.avik.fightclub.domain.Mapper
import com.githubexamples.avik.fightclub.domain.MovieListingRepository
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Single
import io.reactivex.SingleTransformer

/**
 * repository implementation class to abstract the source of movie listing data
 */

class MovieListingRepositoryImpl(
    private val movieListingApiSource: GetMovieListApiSource,
    private val movieListEntityMapper: Mapper<ResultsItem,MovieListItem>
) :
    MovieListingRepository {
    override fun getListOfMovie(pageNumber: Int): Single<List<MovieListItem>> {
        return movieListingApiSource.getListOfMovies(pageNumber).map { return@map it.results }
            .compose(transformFromApiResponse())
            .onErrorReturn { ArrayList() }
    }

    private fun transformFromApiResponse(): SingleTransformer<List<ResultsItem?>?, List<MovieListItem>> {
        return SingleTransformer { allMovies ->
            allMovies.flattenAsObservable { return@flattenAsObservable it }
                .map { eachMovie ->
                    return@map movieListEntityMapper.mapFrom(eachMovie)
                }.toList()

        }

    }

}