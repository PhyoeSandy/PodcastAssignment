package com.padcmyanmar.padcx.podcastassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.SmallPlayerViewPod
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface DetailsPresenter : SmallPlayerViewPod.Delegate, BasePresenter<DetailsView> {
    fun onUiReady(lifecycleOwner: LifecycleOwner, podcastId: String)
}