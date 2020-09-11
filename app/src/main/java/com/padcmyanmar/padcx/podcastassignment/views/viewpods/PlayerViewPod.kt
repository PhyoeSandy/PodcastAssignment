package com.padcmyanmar.padcx.podcastassignment.views.viewpods

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.padcmyanmar.padcx.podcastassignment.delegates.PlayerViewItemDelegate
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.shared.extensions.convertTime
import com.padcmyanmar.padcx.shared.extensions.loadImage
import kotlinx.android.synthetic.main.playe_view_small.view.*
import kotlinx.android.synthetic.main.player_view.view.*
import kotlinx.android.synthetic.main.player_view.view.btnPlay
import kotlinx.android.synthetic.main.player_view.view.tv15
import kotlinx.android.synthetic.main.player_view.view.tv30

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class PlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var mDelegate : PlayerViewItemDelegate ?= null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListener()
    }

    fun setDelegate(delegate: PlayerViewItemDelegate) {
        mDelegate = delegate
    }

    @SuppressLint("NewApi")
    fun setData(data: RandomPodcastVO) {
        ivPodcast.loadImage(data.image)
        tvTitle.text = data.podcast.title
        tvDescription.text = Html.fromHtml(data.description,0)
        tvTime.text = "00:00"
    }

    private fun setupListener() {
        tv15.setOnClickListener {
            mDelegate?.onTap15secBackward()
        }

        tv30.setOnClickListener {
            mDelegate?.onTap30secForward()
        }

        btnPlay.setOnClickListener {
            mDelegate?.onTapPlayButton()
        }
    }


}