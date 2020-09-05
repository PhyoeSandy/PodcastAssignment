package com.padcmyanmar.padcx.shared.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

fun convertTime(sec: Int): String {
    val hr: Int = sec / 3600
    val min: Int = (sec % 3600) / 60

    if (hr <= 0) return "$min m"
    if (min <= 0) return "$hr hr"
    return "$hr hr $min m"
}