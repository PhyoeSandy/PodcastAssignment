package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.podcastassignment.data.model.PodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DetailsPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
class DetailsPresenterImpl : AbstractBasePresenter<DetailsView>(), DetailsPresenter {
    val mPodcastModel: PodcastModel = PodcastModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner, podcastId: String) {
        /* mPodcastModel.getDetailsAndSaveToDb(podcastId, {}, {
             mView?.showErrorMessage(it)
         })*/

        /* mPodcastModel.getDetailsPodcasts(podcastId).observe(lifecycleOwner,
             Observer {
                 it?.let { mView?.showDetails(it) }
             })*/

        mPodcastModel.getDetailsPodcastsById(podcastId)
            .subscribe {
                it?.let { mView?.showDetails(it) }
            }
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {}

    override fun onTap15secForward() {
        Log.d("Tap 15", "Tap 15")
    }

    override fun onTap30secBackward() {
        Log.d("Tap 30", "Tap 30")
    }

    override fun onTapPlayButton(audio: String) {
        mView?.playMusic(audio)
    }

}