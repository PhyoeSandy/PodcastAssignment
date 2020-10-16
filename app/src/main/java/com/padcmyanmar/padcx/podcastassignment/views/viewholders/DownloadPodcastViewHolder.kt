package com.padcmyanmar.padcx.podcastassignment.views.viewholders

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.delegates.PodCastItemDelegate
import com.padcmyanmar.padcx.shared.extensions.loadImage
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_podcasts.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/29/2020.
 */
class DownloadPodcastViewHolder(val delegate: PodCastItemDelegate, itemView: View) :
    BaseViewHolder<DataVO>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.let {
                delegate.onTapPodcast(it.id)
            }
        }
    }

    @SuppressLint("NewApi")
    override fun bindData(items: DataVO) {
        mData = items

        itemView.ivPodcast.loadImage(items.image)
        itemView.tvTitle.text = Html.fromHtml(items.description, 0)

    }
}