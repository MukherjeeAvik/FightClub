package com.githubexamples.avik.fightclub.presentation.adapters

import android.view.ViewGroup
import com.githubexamples.avik.fightclub.base.BaseAdapter
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.domain.entitity.MovieListItem
import com.githubexamples.avik.fightclub.presentation.viewholders.MovieListViewHolder


class MovieListingAdapter () :
    BaseAdapter<MovieListItem, MovieListViewHolder>() {

    private var callback_: BaseViewHolder.ItemClickedCallback<MovieListItem>? = null

    fun registerForCallbacks(callback: BaseViewHolder.ItemClickedCallback<MovieListItem>?) {
        callback_ = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.create(parent, callback_)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.loadData(list[position])
    }

    override fun getItemCount() = listSize

    override fun getItemId(position: Int): Long {
        return list[position].movieId.toLong()
    }


}
