package com.padcmyanmar.padcx.podcastassignment.views.viewholders

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
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
    BaseViewHolder<ItemVO>(itemView) {
    init {
        itemView.setOnClickListener {
            mData?.let {
                delegate.onTapPodcast(it.id) // it.data.id
            }
        }

        itemView.btnDownload.setOnClickListener {
            mData?.let {
                delegate.onTapDownload(it)
            }
        }
    }

    @SuppressLint("NewApi")
    override fun bindData(item: ItemVO) {
        mData = item

        itemView.ivPodcast.loadImage(item.data.image)
        itemView.tvTitle.text = Html.fromHtml(item.data.description, 0)
        itemView.tvTime.text = convertTime(item.data.audio_length_sec)
        //itemView.tvCategoryName.text = item.data.
    }
}