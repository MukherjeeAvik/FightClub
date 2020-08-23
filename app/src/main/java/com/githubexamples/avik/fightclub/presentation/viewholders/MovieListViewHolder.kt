package com.githubexamples.avik.fightclub.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.utils.loadPoster
import kotlinx.android.synthetic.main.cast_item.view.*

class MovieCastViewHolder(itemView: View) : BaseViewHolder<MovieCast>(itemView) {
    override fun loadData(receivedData: MovieCast) {

        itemView.celebPoster.loadPoster(receivedData.profilePic)
        itemView.originalName.text = receivedData.originalName
        itemView.movieName.text = receivedData.characterName

    }


    companion object {
        fun create(
            parent: ViewGroup,
            callBack: ItemClickedCallback<MovieCast>?
        ): MovieCastViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cast_item, parent, false)
            return MovieCastViewHolder(view).also { it.itemClickCallback = callBack }
        }
    }

}