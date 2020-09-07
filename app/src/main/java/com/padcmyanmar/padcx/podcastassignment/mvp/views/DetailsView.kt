package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsResponse
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface DetailsView : BaseView {
    fun showDetails(data: DetailsResponse)
    fun playMusic(audio: String)
}