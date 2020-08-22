package com.githubexamples.avik.fightclub.utils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun View.showAsPer(value: Boolean) {
    if (value)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE

}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}
fun  ImageView.loadPoster(url:String){
    Glide
        .with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this);
}



