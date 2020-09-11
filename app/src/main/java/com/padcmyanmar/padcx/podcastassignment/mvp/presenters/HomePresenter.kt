package com.padcmyanmar.padcx.podcastassignment.mvp.presenters

import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PlayerViewItemDelegate
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.EmptyViewPod
import com.padcmyanmar.padcx.shared.mvp.presenters.BasePresenter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface HomePresenter : EmptyViewPod.Delegate, PlayerViewItemDelegate,
    PodCastItemDelegate, BasePresenter<HomeView> {
    fun saveDownloadItems(data: ItemVO)
}