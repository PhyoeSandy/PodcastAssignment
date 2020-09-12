package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.podcastassignment.data.model.PodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class HomePresenterImpl : AbstractBasePresenter<HomeView>(), HomePresenter {
    val mPodcastModel: PodcastModel = PodcastModelImpl
    lateinit var lifecycleOwner: LifecycleOwner

    override fun onTap15secBackward() {
        mView?.skip15SecBackward()
    }

    override fun onTap30secForward() {
        mView?.skip30SecForward()
    }

    override fun saveDownloadItems(data: ItemVO) {
        mPodcastModel.saveDownloadedItems(data)
    }

    override fun onTapFindSomething() {
        Log.d("onTapFindSomething", "onTapFindSomething")
    }

    override fun onTapReload() {
        Log.d("onTapReload", "onTapReload")
    }

    override fun onTapDownload(item: ItemVO) {
        mView?.downloadingAudio(item)
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        loadDataFromAPI()

        this.lifecycleOwner = lifecycleOwner

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

    override fun onTapPlayButton() {
        mView?.playMusic()
    }

    override fun onTapPodcast(podcastId: Int) {
        mView?.navigateToPodcastDetails(podcastId)
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