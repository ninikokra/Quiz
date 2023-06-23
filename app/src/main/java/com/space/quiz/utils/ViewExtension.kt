package com.space.quiz.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
fun ImageView.setImage(url: String?){
    Glide.with(this).load(url).into(this)
}