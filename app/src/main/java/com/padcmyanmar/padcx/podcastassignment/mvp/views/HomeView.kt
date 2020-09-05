package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface HomeView : BaseView {
    fun displayPlayListInfo(playlist: List<ItemVO>)
    fun displayRandomPodcast(podcast: PodcastVO)
    fun bindDescription(description: String)
    fun navigateToPodcastDetails(podcastId: String)
}