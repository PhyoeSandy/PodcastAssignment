package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.podcastassignment.data.model.FirebasePodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.FirebasePodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DownloadPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DownloadView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class DownloadPresenterImpl : DownloadPresenter, AbstractBasePresenter<DownloadView>() {
    val mPodcastModel: FirebasePodcastModel = FirebasePodcastModelImpl

    override fun onTapFindSomething() {}

    override fun onTapReload() {}

    override fun onTapPodcast(podcastId: String) {
        mView?.navigateToPodcastDetails(podcastId)
    }

    override fun onTapDownload(item: DataVO) {}

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getDownloadsPodcasts().observe(lifecycleOwner,
            Observer {
                mView?.displayDownloadPodcastList(it)
            })
    }
}