package com.padcmyanmar.padcx.podcastassignment.views.viewpods

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.shared.extensions.loadImage
import kotlinx.android.synthetic.main.player_view.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/28/2020.
 */
class PlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mDelegate : Delegate ?= null
    var audio: String = R.string.media_url_mp3.toString()

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListener()
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    @SuppressLint("NewApi")
    fun setData(data: RandomPodcastVO) {
        ivPodcast.loadImage(data.image)
        tvTitle.text = data.podcast.title
        tvDescription.text = Html.fromHtml(data.description,0)
        audio = data.audio
    }

    private fun setupListener() {
        tv15.setOnClickListener {
            mDelegate?.onTap15secForward()
        }

        tv30.setOnClickListener {
            mDelegate?.onTap30secBackward()
        }

        btnPlay.setOnClickListener {
            mDelegate?.onTapPlayButton(audio)
        }
    }

    interface Delegate {
        fun onTap15secForward()
        fun onTap30secBackward()
        fun onTapPlayButton(audio: String)
    }

}