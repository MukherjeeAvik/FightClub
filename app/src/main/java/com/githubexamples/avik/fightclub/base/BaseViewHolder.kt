package com.githubexamples.avik.fightclub.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemClickCallback: ItemClickedCallback<T>? = null


    abstract fun loadData(receivedData: T)
    open fun loadData(data: T, position: Int) {

    }


    interface ItemClickedCallback<T> {
        fun onClicked(data: T)
    }


}
