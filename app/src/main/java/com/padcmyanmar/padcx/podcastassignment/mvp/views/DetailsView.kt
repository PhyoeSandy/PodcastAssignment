package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface DetailsView : BaseView {
    fun showDetails(data: DataVO)
    fun playMusic()
    fun skip15SecBackward()
    fun skip30SecForward()
}