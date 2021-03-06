package com.padcmyanmar.padcx.podcastassignment.delegates

import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface PodCastItemDelegate {

    fun onTapPodcast(podcastId: Int)
    fun onTapDownload(item: ItemVO)
}