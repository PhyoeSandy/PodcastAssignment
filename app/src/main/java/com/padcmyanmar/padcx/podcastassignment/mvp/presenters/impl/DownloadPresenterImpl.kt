package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DownloadPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DownloadView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class DownloadPresenterImpl : DownloadPresenter, AbstractBasePresenter<DownloadView>() {
    override fun onTapFindSomething() {
        //mView?.displayCategoryList()
    }

    override fun onTapReload() {
       mView?.displayDownloadPodcastList()
    }

    override fun onTapPodcast(podcastId: String) {
        mView?.navigateToPodcastDetails(podcastId)
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }
}