package com.githubexamples.avik.fightclub.domain

import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import io.reactivex.Single

interface MovieListingRepository{
    fun getListOfMovie(pageNumber:Int): Single<List<MovieListItem>>
}