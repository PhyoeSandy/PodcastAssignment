package com.padcmyanmar.padcx.podcastassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.podcastassignment.views.viewholders.DownloadPodcastViewHolder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/29/2020.
 */
class DownloadPodcastAdapter(delegate: PodCastItemDelegate) : BaseRecyclerAdapter<DownloadPodcastViewHolder, DataVO>() {
    private val mDelegate = delegate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadPodcastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_downloads, parent, false)
        return DownloadPodcastViewHolder(mDelegate, view)
    }
}