package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface HomeView : BaseView {
    fun displayPlayListInfo(playlist: List<ItemVO>)
    fun displayRandomPodcast(podcast: RandomPodcastVO)
    fun bindDescription(description: String)
    fun navigateToPodcastDetails(podcastId: Int)
    fun playMusic()
    fun downloadingAudio(data: ItemVO)
    fun skip15SecBackward()
    fun skip30SecForward()
}