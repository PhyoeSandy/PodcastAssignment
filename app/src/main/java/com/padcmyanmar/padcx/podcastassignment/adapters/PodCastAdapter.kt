package com.padcmyanmar.padcx.podcastassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.podcastassignment.views.viewholders.PodcastViewHolder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
class PodCastAdapter(delegate: PodCastItemDelegate) :
    BaseRecyclerAdapter<PodcastViewHolder, DataVO>() {
    private val mDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_podcasts, parent, false)
        return PodcastViewHolder(mDelegate,view)
    }
}