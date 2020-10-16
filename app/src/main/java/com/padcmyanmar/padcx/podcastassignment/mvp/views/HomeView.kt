package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface HomeView : BaseView {
    fun displayRandomPodcast(podcast: DataVO)
    fun bindDescription(description: String)
    fun navigateToPodcastDetails(podcastId: String)
    fun playMusic()
    fun skip15SecBackward()
    fun skip30SecForward()
    fun displayPlayListInfoByFb(playlist: List<DataVO>)
    fun downloadingAudioByFb(data: DataVO)

}