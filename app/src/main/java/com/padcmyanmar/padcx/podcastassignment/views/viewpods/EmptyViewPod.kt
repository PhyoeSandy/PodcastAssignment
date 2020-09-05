package com.padcmyanmar.padcx.podcastassignment.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.viewpod_empty.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/29/2020.
 */
class EmptyViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mDelegate: Delegate ?= null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListener()
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    private fun setupListener() {
        btnNew.setOnClickListener {
            mDelegate?.onTapFindSomething()
        }

        btnReload.setOnClickListener {
            mDelegate?.onTapReload()
        }
    }

    interface Delegate {
        fun onTapFindSomething()
        fun onTapReload()
    }
}