package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.podcastassignment.data.model.PodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class HomePresenterImpl : AbstractBasePresenter<HomeView>(), HomePresenter {
    val mPodcastModel: PodcastModel = PodcastModelImpl

    override fun onTap15secForward() {
        Log.d("onTap15secForward", "onTap15secForward")
    }

    override fun onTap30secBackward() {
        Log.d("onTap30secBackward", "onTap30secBackward")
    }


    override fun onTapFindSomething() {
        Log.d("onTapFindSomething", "onTapFindSomething")
    }

    override fun onTapReload() {
        Log.d("onTapReload", "onTapReload")
    }

    override fun onTapPodcast(podcastId: String) {
        mView?.navigateToPodcastDetails(podcastId)
    }

    override fun onTapDownload(podcastId: String) {
        mPodcastModel.getDetailsPodcastsById(podcastId).subscribe {
            it?.let {
                mView?.downloadingAudio(it.audio)
            }
        }
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        //loadDataFromAPI()

        mPodcastModel.getPlayListPodcasts().observe(lifecycleOwner,
            Observer {
                it?.let { mView?.displayPlayListInfo(it) }
            })

        mPodcastModel.getRandomPodcast().observe(lifecycleOwner,
            Observer {
                it?.let {
                    mView?.displayRandomPodcast(it)
                    mView?.bindDescription(it.description)
                }
            })
    }

    override fun onTapPlayButton(audio: String) {
        mView?.playMusic(audio)
    }

    private fun loadDataFromAPI() {
        mPodcastModel.getRandomPodcastAndSaveToDb({}, {
            mView?.showErrorMessage(it)
        })

        mPodcastModel.getPlayListAndSaveToDb({}, {
            mView?.showErrorMessage(it)
        })
    }

}