package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.podcastassignment.data.model.FirebasePodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.PodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.FirebasePodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DetailsPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
class DetailsPresenterImpl : AbstractBasePresenter<DetailsView>(), DetailsPresenter {
    val mPodcastModel: FirebasePodcastModel = FirebasePodcastModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner, podcastId: Int) {

        mPodcastModel.getDetailsPodcasts(podcastId).observe(lifecycleOwner,
            Observer {
                it?.let { mView?.showDetails(it) }
            })
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {}

    override fun onTap15secBackward() {
        Log.d("Tap 15", "Tap 15")
    }

    override fun onTap30secForward() {
        Log.d("Tap 30", "Tap 30")
    }

    override fun onTapPlayButton() {
        mView?.playMusic()
    }

}