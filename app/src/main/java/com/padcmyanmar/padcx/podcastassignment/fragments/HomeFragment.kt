package com.padcmyanmar.padcx.podcastassignment.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.activities.PodCastDetailsActivity
import com.padcmyanmar.padcx.podcastassignment.adapters.PodCastAdapter
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.HomePresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.EmptyViewPod
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.PlayerViewPod
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.player_view.*
import java.util.concurrent.TimeUnit


/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment(), HomeView {
    companion object {
        const val REQUEST_CODE = 100

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private lateinit var mPodCastAdapter: PodCastAdapter
    private lateinit var mPresenter: HomePresenter

    private lateinit var mEmptyViewPod: EmptyViewPod
    private lateinit var exoPlayerViewPod: PlayerViewPod

    var exoPlayer: SimpleExoPlayer? = null
    private var playbackPosition: Long = 0
    private val forwardSpeed = 30000 //30sec
    private val backwardSpeed = 15000 //15sec
    private var isPlaying = false
    var items: ItemVO? = null
    var playWhenReady = false

    val progressBarListener = object : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            super.onPlayerStateChanged(playWhenReady, playbackState)

            if (playbackState == ExoPlayer.STATE_BUFFERING) {
                playbackPosition = TimeUnit.MILLISECONDS.toSeconds(exoPlayer!!.currentPosition)

                /*tvTime.text = String.format(
                    "%02d : %02d", (playbackPosition % 3600) / 60,
                    (playbackPosition % 3600) % 60
                )*/

                progressbar.max = (exoPlayer?.duration)!!.toInt()
                progressbar.progress =
                    ((exoPlayer!!.currentPosition * 100) / exoPlayer?.duration!!).toInt()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupRecyclerView()
        setupViewPod()

        mPresenter.onUiReady(this)
    }

    private fun setupViewPod() {
        mEmptyViewPod = vpEmpty as EmptyViewPod
        mEmptyViewPod.setDelegate(mPresenter)

        exoPlayerViewPod = vpLargeMedia as PlayerViewPod
        exoPlayerViewPod.setDelegate(mPresenter)
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setupRecyclerView() {
        mPodCastAdapter = PodCastAdapter(mPresenter)
        with(rvPodcasts) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mPodCastAdapter
        }
    }

    override fun displayPlayListInfo(playlist: List<ItemVO>) {
        mPodCastAdapter.setNewData(playlist.toMutableList())
    }

    override fun displayRandomPodcast(podcast: RandomPodcastVO) {
        exoPlayerViewPod.setData(podcast)
        //initializePlayer(podcast.audio)
        setUpMediaPlayer(podcast.audio)
    }

    @SuppressLint("NewApi")
    override fun bindDescription(description: String) {
        tvDetails.text = Html.fromHtml(description, 0)
    }

    override fun navigateToPodcastDetails(podcastId: Int) {
        startActivity(activity?.let {
            PodCastDetailsActivity.newIntent(it, podcastId)
        })
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

    override fun downloadingAudio(data: ItemVO) {
        items = data
        checkPermission()
    }

    override fun skip15SecBackward() {
        move15SecBackward()
    }

    override fun skip30SecForward() {
        move30SecForward()
    }

    private fun download() {
        var request = DownloadManager.Request(Uri.parse(items?.data?.audio))
            .setTitle("Podcast Audio")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setAllowedOverMetered(true)

        var downloadManager: DownloadManager =
            activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "${items?.data?.title?.trim()?.substring(0, 15)}.mp3"
        );
        var myDownloadId = downloadManager.enqueue(request)

        var br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                var id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == myDownloadId) {
                    activity?.let {
                        Toast.makeText(
                            it.applicationContext,
                            "Download Complete",
                            Toast.LENGTH_LONG
                        ).show()

                        items?.let { downloadData ->
                            mPresenter.saveDownloadItems(downloadData)
                        }
                    }
                }
            }
        }
        activity?.registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "Granted success", Toast.LENGTH_LONG)
                } else {
                    // explain user
                }
                return
            }
            else -> {

            }
        }
    }

    private fun checkPermission() {
        when {
            context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            } == PackageManager.PERMISSION_GRANTED -> {
                context?.let {
                    download()
                }
            }
            else -> {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE
                )
            }
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Home Fragment: ", error)
    }

    fun initializePlayer(audio: String) {
        activity?.let {
            exoPlayer = SimpleExoPlayer.Builder(it.applicationContext).build()
            // Produces DataSource instances through which media data is loaded.
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                it.applicationContext, Util.getUserAgent(it.applicationContext, "Podcast")
            )

            // This is the MediaSource representing the media to be played.
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(audio))

            exoPlayer?.playWhenReady = playWhenReady
            exoPlayer?.addListener(progressBarListener)

            // Prepare the player with the source.
            exoPlayer?.prepare(mediaSource)
        }

    }

    private fun setUpMediaPlayer(audio: String) {
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

    }

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