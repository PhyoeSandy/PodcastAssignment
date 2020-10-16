package com.padcmyanmar.padcx.podcastassignment.views.viewholders

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.shared.extensions.convertTime
import com.padcmyanmar.padcx.shared.extensions.loadImage
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_podcasts.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
class PodcastViewHolder(delegate: PodCastItemDelegate, itemView: View) :
    BaseViewHolder<DataVO>(itemView) {
    init {
        itemView.setOnClickListener {
            mData?.let {
                delegate.onTapPodcast(it.id)
            }
        }

        itemView.btnDownload.setOnClickListener {
            mData?.let {
                delegate.onTapDownload(it)
            }
        }
    }

    @SuppressLint("NewApi")
    override fun bindData(item: DataVO) {
        mData = item

        itemView.ivPodcast.loadImage(item.image)
        itemView.tvTitle.text = Html.fromHtml(item.description, 0)
        itemView.tvTime.text = convertTime(item.audio_length_sec)
    }
}