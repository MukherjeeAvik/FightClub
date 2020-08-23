package com.githubexamples.avik.fightclub.presentation.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githubexamples.avik.fightclub.R
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.utils.loadPoster
import kotlinx.android.synthetic.main.recent_search_item.view.*


class RecentMoviesViewHolder(itemView: View) : BaseViewHolder<MovieListItem>(itemView) {
    override fun loadData(receivedData: MovieListItem) {
        itemView.moviePoster.loadPoster(receivedData.moviePoster)
    }


    companion object {
        fun create(
            parent: ViewGroup,
            callBack: ItemClickedCallback<MovieListItem>?
        ): RecentMoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recent_search_item, parent, false)
            return RecentMoviesViewHolder(view).also { it.itemClickCallback = callBack }
        }
    }

}