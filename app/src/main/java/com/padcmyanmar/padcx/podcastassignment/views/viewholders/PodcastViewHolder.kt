package com.padcmyanmar.padcx.podcastassignment.views.viewholders

import android.view.View
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.podcastassignment.network.responses.PlaylistsVO
import com.padcmyanmar.padcx.shared.extensions.loadImage
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_podcasts.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
class PodcastViewHolder(delegate: PodCastItemDelegate, itemView: View) : BaseViewHolder<PlaylistsVO>(itemView) {
    init {
        itemView.setOnClickListener{
            mData?.let {
                delegate.onTapPodcast(it.id)
            }
        }
    }

    override fun bindData(data: PlaylistsVO) {
        mData = data

        itemView.ivPodcast.loadImage(data.image)
        itemView.tvTitle.text = data.description
    }
}