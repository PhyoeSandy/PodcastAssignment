package com.padcmyanmar.padcx.podcastassignment

import android.app.Application
import com.google.android.exoplayer2.SimpleExoPlayer
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
class PodcastApp : Application() {

    companion object {
        var exoPlayer: SimpleExoPlayer? = null
    }

    override fun onCreate() {
        super.onCreate()
        PodcastModelImpl.initDatabase(applicationContext)

        exoPlayer = SimpleExoPlayer.Builder(applicationContext).build()
    }


}