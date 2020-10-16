package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.podcastassignment.data.model.FirebasePodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.FirebasePodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class HomePresenterImpl : AbstractBasePresenter<HomeView>(), HomePresenter {
    val mPodcastModel: FirebasePodcastModel = FirebasePodcastModelImpl
    lateinit var lifecycleOwner: LifecycleOwner

    override fun onTap15secBackward() {
        mView?.skip15SecBackward()
    }

    override fun onTap30secForward() {
        mView?.skip30SecForward()
    }

    override fun saveDownloadItems(data: DataVO) {
        mPodcastModel.saveDownloadedItems(data)
    }

    override fun onTapFindSomething() {
        Log.d("onTapFindSomething", "onTapFindSomething")
    }

    override fun onTapReload() {
        Log.d("onTapReload", "onTapReload")
    }

    override fun onTapDownload(item: DataVO) {
        mView?.downloadingAudioByFb(item)
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        loadDataFromAPI()

        this.lifecycleOwner = lifecycleOwner

        mPodcastModel.getPlayListPodcasts().observe(lifecycleOwner,
             Observer {
                 it?.let { mView?.displayPlayListInfoByFb(it) }
             })

        mPodcastModel.getRandomPodcast().observe(lifecycleOwner,
            Observer {
                it?.let {
                    mView?.displayRandomPodcast(it)
                    mView?.bindDescription(it.description)
                }
            })
    }

    override fun onTapPlayButton() {
        mView?.playMusic()
    }

    override fun onTapPodcast(podcastId: String) {
        mView?.navigateToPodcastDetails(podcastId)
    }

    private fun loadDataFromAPI() {
        mPodcastModel.getPlayListPodcasts({
           /* it?.let {
                mView?.displayPlayListInfoByFb(it)
            }*/
        }, {
            mView?.showErrorMessage(it)
        })
    }

}