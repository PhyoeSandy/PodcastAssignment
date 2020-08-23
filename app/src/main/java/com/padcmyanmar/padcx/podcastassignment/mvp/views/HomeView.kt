package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface HomeView : BaseView {
    fun displayPodcastList(podcasts: List<PodcastVO>)
    fun navigateToPodcastDetails()
}