package com.padcmyanmar.padcx.podcastassignment.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.delegates.PlayerViewItemDelegate
import com.padcmyanmar.padcx.shared.extensions.convertTime
import kotlinx.android.synthetic.main.playe_view_small.view.*
import kotlinx.android.synthetic.main.player_view.view.tv15
import kotlinx.android.synthetic.main.player_view.view.tv30
import java.util.concurrent.TimeUnit

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/5/2020.
 */
class SmallPlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var mDelegate: PlayerViewItemDelegate? = null
    var audio: String = ""

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListener()
    }

    fun setDelegate(delegate: PlayerViewItemDelegate) {
        mDelegate = delegate
    }

    fun setData(duration: Long) {
        tvStartTime.text = "00:00"
        tvEndTime.text = convertTime(TimeUnit.MILLISECONDS.toSeconds(duration).toInt())
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