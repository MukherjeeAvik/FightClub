package com.githubexamples.avik.fightclub.utils
import android.view.View




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



