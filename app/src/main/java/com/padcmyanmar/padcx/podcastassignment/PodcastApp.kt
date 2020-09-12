package com.padcmyanmar.padcx.podcastassignment

import android.app.Application
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
class PodcastApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PodcastModelImpl.initDatabase(applicationContext)
    }


}