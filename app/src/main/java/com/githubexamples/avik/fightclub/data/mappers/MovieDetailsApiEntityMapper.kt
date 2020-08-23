package com.githubexamples.avik.fightclub.data.mappers

import com.githubexamples.avik.fightclub.data.entities.GenresItem
import com.githubexamples.avik.fightclub.data.entities.MovieDetailsResponse
import com.githubexamples.avik.fightclub.domain.Mapper
import com.githubexamples.avik.fightclub.domain.entitity.MovieDetails
import com.githubexamples.avik.fightclub.utils.IMAGE_URL_PREFIX

class MovieDetailsApiEntityMapper : Mapper<MovieDetailsResponse, MovieDetails>() {
    override fun mapFrom(from: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            title = from.title ?: "-",
            desc = from.overview ?: "-",
            genres = getGenre(from.genres),
            posterPath = IMAGE_URL_PREFIX + from.posterPath.toString(),
            backdrop = IMAGE_URL_PREFIX + from.backdropPath.toString(),
            rating = from.voteAverage.toString()

        )
    }

    private fun getGenre(list: List<GenresItem?>?): List<String> {
        val listOfGenres = ArrayList<String>()
        list?.forEach { eachGenreItem ->
            listOfGenres.add(eachGenreItem?.name ?: "")

        }
        return listOfGenres
    }

}
