package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface DownloadView : BaseView {
    fun displayDownloadPodcastList(downloads: List<ItemVO>)
    fun displayCategoryList(categories: List<CategoryVO>)
    fun navigateToPodcastDetails(podcastId: String)
}