package com.padcmyanmar.padcx.podcastassignment.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.padcmyanmar.padcx.shared.extensions.convertTime
import com.padcmyanmar.padcx.shared.extensions.loadImage
import kotlinx.android.synthetic.main.playe_view_small.view.*
import kotlinx.android.synthetic.main.player_view.view.*
import kotlinx.android.synthetic.main.player_view.view.tv15
import kotlinx.android.synthetic.main.player_view.view.tv30

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/5/2020.
 */
class SmallPlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var mDelegate: Delegate ?= null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListener()
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    fun setData(duration: Int) {
        tvStartTime.text = "00:00"
        tvEndTime.text = convertTime(duration)
    }

    private fun setupListener() {
        tv15.setOnClickListener {
            mDelegate?.onTap15secForward()
        }

        tv30.setOnClickListener {
            mDelegate?.onTap30secBackward()
        }
    }

    interface Delegate {
        fun onTap15secForward()
        fun onTap30secBackward()
    }

}