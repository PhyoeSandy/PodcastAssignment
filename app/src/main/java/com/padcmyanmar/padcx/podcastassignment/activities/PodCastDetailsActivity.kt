package com.padcmyanmar.padcx.podcastassignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DetailsPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.DetailsPresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsVO
import com.padcmyanmar.padcx.podcastassignment.utils.DETAILS_ID
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.SmallPlayerViewPod
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_podcast_details.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
class PodCastDetailsActivity : BaseActivity(), DetailsView {

    companion object {
        const val IE_PODCAST_ID = "IE_PODCAST_ID"

        fun newIntent(context: Context, podcastId: String): Intent {
            val intent = Intent(context, PodCastDetailsActivity::class.java)
            intent.putExtra(IE_PODCAST_ID,podcastId)
            return intent
        }
    }

    private lateinit var mPresenter: DetailsPresenter
    private lateinit var mSmallPlayerViewPod: SmallPlayerViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_details)

        setupPresenter()
        setupViewPod()

        //intent.getStringExtra(IE_PODCAST_ID)?.let { mPresenter.onUiReady(this, it) }
        mPresenter.onUiReady(this, DETAILS_ID)
    }

    private fun setupViewPod() {
        mSmallPlayerViewPod = vpSmallMedia as SmallPlayerViewPod
        mSmallPlayerViewPod.setDelegate(mPresenter)
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProviders.of(this)
            .get(DetailsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun showDetails(data: DetailsVO) {
        tvTitle.text = data.podcast.title
        tvDescription.text = data.description
        mSmallPlayerViewPod.setData(data.audio_length_sec)

    }

    override fun showErrorMessage(error: String) {
        Log.e("PodcastDetails : ", error)
    }
}