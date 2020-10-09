package com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcmyanmar.padcx.podcastassignment.data.model.FirebasePodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.PodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.FirebasePodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.data.model.impls.PodcastModelImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.SearchPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.views.SearchView
import com.padcmyanmar.padcx.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
class SearchPresenterImpl : AbstractBasePresenter<SearchView>(), SearchPresenter {
    val mPodcastModel: FirebasePodcastModel = FirebasePodcastModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

        mPodcastModel.getCategoryList({}, {
            mView?.showErrorMessage(it)
        })

        mPodcastModel.getCategoryList().observe(
            lifecycleOwner, Observer {
                it?.let {
                    mView?.displayCategoryList(it)
                    if (it.isNotEmpty()) {
                        mView?.bindData(it[0])
                    }
                }
            }
        )
    }
}