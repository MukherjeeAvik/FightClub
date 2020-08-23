package com.githubexamples.avik.fightclub.data.mappers

import com.githubexamples.avik.fightclub.data.entities.CastItem
import com.githubexamples.avik.fightclub.domain.Mapper
import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.utils.IMAGE_URL_PREFIX

class MovieCastApiEntityMapper : Mapper<CastItem, MovieCast>() {
    override fun mapFrom(from: CastItem): MovieCast {
        return MovieCast(
            profilePic = IMAGE_URL_PREFIX + from.profilePath.toString(),
            originalName = from.name ?: "",
            characterName = from.character ?: ""

        )
    }
}