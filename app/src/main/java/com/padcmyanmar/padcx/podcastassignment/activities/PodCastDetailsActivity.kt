package com.padcmyanmar.padcx.podcastassignment.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DetailsPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.DetailsPresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DetailsView
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.SmallPlayerViewPod
import com.padcmyanmar.padcx.shared.activities.BaseActivity
import com.padcmyanmar.padcx.shared.extensions.loadImage
import kotlinx.android.synthetic.main.activity_podcast_details.*
import kotlinx.android.synthetic.main.player_view.btnPlay
import kotlinx.android.synthetic.main.player_view.progressbar
import java.util.concurrent.TimeUnit

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

    var exoPlayer: SimpleExoPlayer ?= null
    var playWhenReady = false
    var currentWindow = 0
    var playBackPosition = 0L

    private var playbackPosition: Long = 0
    private val forwardSpeed = 30000 //30sec
    private val backwardSpeed = 15000 //15sec
    private var isPlaying = false

    val progressBarListener = object : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            super.onPlayerStateChanged(playWhenReady, playbackState)

            if (playbackState == ExoPlayer.STATE_BUFFERING) {
                playbackPosition = TimeUnit.MILLISECONDS.toSeconds(exoPlayer!!.currentPosition)

                /*tvStartTime.text = String.format(
                    "%02d : %02d", (playbackPosition % 3600) / 60,
                    (playbackPosition % 3600) % 60
                )*/

                progressbar.max = (exoPlayer?.duration)!!.toInt()
                progressbar.progress =
                    ((exoPlayer!!.currentPosition * 100) / exoPlayer?.duration!!).toInt()
            }

        }
    }


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
    override fun showDetails(data: DataVO) {
        tvTitle.text = data.podcast?.title
        tvDescription.text = Html.fromHtml(data.description, 0)
        ivPodcast.loadImage(data.image)
        mSmallPlayerViewPod.setData(data.audio_length_sec.toLong())
        initializePlayer(data.audio)
    }

    override fun playMusic() {
        if (!isPlaying) {
            exoPlayer?.playWhenReady = true
            btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
            isPlaying = true
        } else {
            exoPlayer?.playWhenReady = false
            btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            isPlaying = false
        }
    }

    override fun skip15SecBackward() {
        move15SecBackward()
    }

    override fun skip30SecForward() {
        move30SecForward()
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

    fun initializePlayer(audio: String) {
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this, Util.getUserAgent(this, "Podcast")
        )

        // This is the MediaSource representing the media to be played.
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(audio))

        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.addListener(progressBarListener)

        // Prepare the player with the source.
        exoPlayer?.prepare(mediaSource)
    }

    /*private fun setUpMediaPlayer(audio: String) {
        activity?.let {
            val defaultRenderersFactory = DefaultRenderersFactory(it.applicationContext)
            exoPlayer =
                SimpleExoPlayer.Builder(it.applicationContext, defaultRenderersFactory).build()
            val userAgent = Util.getUserAgent(it.applicationContext, "The PodCast App")
            val mediaSource = ExtractorMediaSource(
                Uri.parse(audio),
                DefaultDataSourceFactory(it.applicationContext, userAgent),
                DefaultExtractorsFactory(),
                null,
                null
            )
            exoPlayer?.addListener(progressBarListener)
            exoPlayer?.prepare(mediaSource)
        }
    }*/

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    fun releasePlayer() {
        if (exoPlayer != null) {
            playWhenReady = exoPlayer!!.playWhenReady
            playbackPosition = exoPlayer!!.currentPosition
            exoPlayer?.removeListener(progressBarListener)
            exoPlayer?.release()
            exoPlayer = null
        }
    }

    fun move30SecForward() {
        playbackPosition += forwardSpeed
        exoPlayer?.seekTo(playbackPosition)
    }

    fun move15SecBackward() {
        playbackPosition -= backwardSpeed
        exoPlayer?.seekTo(playbackPosition)
    }

}