package com.padcmyanmar.padcx.podcastassignment.views.viewholders

import android.view.View
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/29/2020.
 */
class DownloadPodcastViewHolder(val delegate: PodCastItemDelegate, itemView: View) :
    BaseViewHolder<PodcastVO>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.let {
                delegate.onTapPodcast(it.id)
            }
        }
    }

    override fun bindData(data: PodcastVO) {

    }
}