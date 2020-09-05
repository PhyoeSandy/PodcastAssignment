package com.padcmyanmar.padcx.shared.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.padcx.shared.mvp.views.BaseView


/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
interface BasePresenter<T: BaseView> {
    fun initPresenter(view:T)
    fun onUiReady(lifecycleOwner: LifecycleOwner)
}