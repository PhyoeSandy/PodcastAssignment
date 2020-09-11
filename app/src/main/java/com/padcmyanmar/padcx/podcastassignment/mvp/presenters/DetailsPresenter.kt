package com.padcmyanmar.padcx.podcastassignment.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.podcastassignment.delegates.PlayerViewItemDelegate
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface DetailsPresenter : PlayerViewItemDelegate, BasePresenter<DetailsView> {
    fun onUiReady(lifecycleOwner: LifecycleOwner, podcastId: String)
}