package com.githubexamples.avik.fightclub.presentation.adapters


import android.view.ViewGroup
import com.githubexamples.avik.fightclub.base.BaseAdapter
import com.githubexamples.avik.fightclub.base.BaseViewHolder
import com.githubexamples.avik.fightclub.domain.entitity.MovieCast
import com.githubexamples.avik.fightclub.presentation.viewholders.MovieCastViewHolder

class MovieCastAdapter:
    BaseAdapter<MovieCast, MovieCastViewHolder>() {

    private var callback_: BaseViewHolder.ItemClickedCallback<MovieCast>? = null

    fun registerForCallbacks(callback: BaseViewHolder.ItemClickedCallback<MovieCast>?) {
        callback_ = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        return MovieCastViewHolder.create(parent, callback_)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        holder.loadData(list[position])
    }

    override fun getItemCount() = listSize


}