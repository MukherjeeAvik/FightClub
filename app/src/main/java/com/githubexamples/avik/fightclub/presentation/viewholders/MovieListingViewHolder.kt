package com.githubexamples.avik.fightclub.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.utils.loadPoster
import kotlinx.android.synthetic.main.movie_list_item.view.*


class MovieListViewHolder(itemView: View) : BaseViewHolder<MovieListItem>(itemView) {
    override fun loadData(receivedData: MovieListItem) {
        itemView.movieNameTxt.text = receivedData.movieTitle
        itemView.releaseDateTxt.text = receivedData.releaseData
        itemView.moviePoster.loadPoster(receivedData.moviePoster)

    }


    companion object {
        fun create(
            parent: ViewGroup,
            callBack: ItemClickedCallback<MovieListItem>?
        ): MovieListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
            return MovieListViewHolder(view).also { it.itemClickCallback = callBack }
        }
    }

}
