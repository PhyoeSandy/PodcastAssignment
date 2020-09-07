package com.padcmyanmar.padcx.shared.extensions

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/7/2020.
 */
fun initializePlayer(audio: String, player: SimpleExoPlayer, context: Context) {

// Produces DataSource instances through which media data is loaded.
    val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
        context, Util.getUserAgent(context, "Podcast")
    )

// This is the MediaSource representing the media to be played.
    val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"))

// Prepare the player with the source.
    player.prepare(mediaSource)
    Log.d("Player: ","play.")
}

fun releasePlayer(player:SimpleExoPlayer) {
    player.release()
    Log.d("Player: ","release.")
}