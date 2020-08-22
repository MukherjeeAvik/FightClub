package com.githubexamples.avik.fightclub.data.mappers

import com.githubexamples.avik.fightclub.data.entities.ResultsItem
import com.githubexamples.avik.fightclub.domain.Mapper
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.utils.IMAGE_URL_PREFIX
import javax.inject.Inject

/**
 * mapper implementation class that converts
 * api response model for movie listing api  to domain model
 */

class MovieListingApiEntityMapper: Mapper<ResultsItem, MovieListItem>() {
    override fun mapFrom(from: ResultsItem): MovieListItem {
       return MovieListItem(
           movieId =  from.id.toString(),
           movieTitle = from.title?:"",
           releaseData =  from.releaseDate.toString(),
           moviePoster =  IMAGE_URL_PREFIX + from.posterPath.toString()

       )
    }

}