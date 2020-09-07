package com.padcmyanmar.padcx.podcastassignment.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.SimpleExoPlayer
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DetailsPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.DetailsPresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsResponse
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
            intent.putExtra(IE_PODCAST_ID, podcastId)
            return intent
        }
    }

    private lateinit var mPresenter: DetailsPresenter
    private lateinit var mSmallPlayerViewPod: SmallPlayerViewPod

    lateinit var exoPlayer: SimpleExoPlayer
    var playWhenReady = false
    var currentWindow = 0
    var playBackPosition = 0L
    var audio: String = "https://www.listennotes.com/e/p/02f0123246c944e289ee2bb90804e41b/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_details)

        setupPresenter()
        setupViewPod()

        intent.getStringExtra(IE_PODCAST_ID)?.let { mPresenter.onUiReady(this, it) }
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

    @SuppressLint("NewApi")
    override fun showDetails(data: DetailsResponse) {
        tvTitle.text = data.podcast.title
        tvDescription.text = Html.fromHtml(data.description,0)
        mSmallPlayerViewPod.setData(data.audio_length_sec, data.audio)
    }

    override fun playMusic(audio: String) {
        this.audio = audio
        /*btnPlay.setOnClickListener {
            if (!playWhenReady) {
                playWhenReady = true
                initializePlayer(audio)
                btnPlay.setImageResource(R.drawable.exo_icon_play)
            } else {
                playWhenReady = false
                exoPlayer.setPlayWhenReady(playWhenReady)
                btnPlay.setImageResource(R.drawable.exo_icon_pause)
            }
        }*/
    }

    override fun showErrorMessage(error: String) {
        Log.e("PodcastDetails : ", error)
    }

    /*override fun onStart() {
        if (Util.SDK_INT >= 24) {
            initializePlayer(audio)
        }
        super.onStart()
    }

    override fun onResume() {
        if (Util.SDK_INT < 24 || exoPlayer == null) {
            initializePlayer(audio)
        }
        super.onResume()
    }

    override fun onPause() {
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
        super.onPause()
    }

    override fun onStop() {
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
        super.onStop()
    }

    private fun initializePlayer(audio: String) {
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        //val uri = Uri.parse(audio)
        val uri = Uri.parse(applicationContext.getString(R.string.media_url_mp3))
        val mediaSource = buildMediaSource(uri)
        exoPlayer.setPlayWhenReady(playWhenReady)
        exoPlayer.seekTo(currentWindow, playBackPosition)
        exoPlayer.prepare(mediaSource!!, false, false)
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(applicationContext, "podcast_assignment")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.playWhenReady = false
            playBackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentWindowIndex
            exoPlayer.stop()
            //exoPlayer.release()
        }
    }*/

}