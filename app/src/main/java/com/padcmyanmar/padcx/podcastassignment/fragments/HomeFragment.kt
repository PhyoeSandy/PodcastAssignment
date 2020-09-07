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
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
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
import com.padcmyanmar.padcx.shared.extensions.initializePlayer
import com.padcmyanmar.padcx.shared.extensions.releasePlayer
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.player_view.*


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
    private lateinit var mPlayerViewPod: PlayerViewPod

    var audio: String = "https://www.listennotes.com/e/p/02f0123246c944e289ee2bb90804e41b/"

    lateinit var exoPlayer: SimpleExoPlayer
    var isPlaying = false
    var currentWindow = 0
    var playBackPosition = 0L

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
        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()

        btnPlay.setOnClickListener {
            if (!isPlaying) {
                //initializePlayer(audio, exoPlayer, requireContext())
                setUpMediaPlayer()
                Log.d("Player: ", "played.")
                isPlaying = true
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
            } else {
                //releasePlayer(exoPlayer)
                exoPlayer.stop()
                Log.d("Player: ", "paused.")
                btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                isPlaying = false
            }
        }
    }

    private fun setupViewPod() {
        mEmptyViewPod = vpEmpty as EmptyViewPod
        mEmptyViewPod.setDelegate(mPresenter)

        mPlayerViewPod = vpLargeMedia as PlayerViewPod
        mPlayerViewPod.setDelegate(mPresenter)
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
        mPlayerViewPod.setData(podcast)
    }

    @SuppressLint("NewApi")
    override fun bindDescription(description: String) {
        tvDetails.text = Html.fromHtml(description, 0)
    }

    override fun navigateToPodcastDetails(podcastId: String) {
        startActivity(activity?.let {
            PodCastDetailsActivity.newIntent(it, podcastId)
        })
    }

    override fun playMusic(audio: String) {
        this.audio = audio
        btnPlay.setOnClickListener {
            if (!isPlaying) {
                //initializePlayer(audio, exoPlayer, requireContext())
                setUpMediaPlayer()
                Log.d("Player: ", "played.")
                isPlaying = true
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
            } else {
                //releasePlayer(exoPlayer)
                Log.d("Player: ", "paused.")
                btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                isPlaying = false
            }
        }
    }

    override fun downloadingAudio(audio: String) {
        checkPermission()
        download()
    }

    private fun download() {
        var request = DownloadManager.Request(Uri.parse(getString(R.string.media_url_mp3)))
            .setTitle("Downing Audio")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setAllowedOverMetered(true)

        var downloadManager: DownloadManager =
            activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "podcastApp.mp3"
        );
        var myDownloadId = downloadManager.enqueue(request)

        var br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                var id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == myDownloadId) {
                    activity?.let {
                        Toast.makeText(
                            it.applicationContext,
                            "Download Complete", Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        activity?.registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
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

     override fun onResume() {
         if (Util.SDK_INT < 24 || exoPlayer == null) {
             setUpMediaPlayer()
         }
         super.onResume()
     }

     override fun onPause() {
         if (Util.SDK_INT < 24) {
             releasePlayer(exoPlayer)
         }
         super.onPause()
     }

     override fun onStop() {
         if (Util.SDK_INT >= 24) {
             releasePlayer(exoPlayer)
         }
         super.onStop()
     }

    private fun setUpMediaPlayer() {
        activity?.let {
            val defaultRenderersFactory = DefaultRenderersFactory(it.applicationContext)
            exoPlayer =
                SimpleExoPlayer.Builder(it.applicationContext, defaultRenderersFactory).build()
            val userAgent = Util.getUserAgent(it.applicationContext, "The PodCast App")
            val mediaSource = ExtractorMediaSource(
                Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/Jazz_In_Paris.mp3"),
                DefaultDataSourceFactory(it.applicationContext, userAgent),
                DefaultExtractorsFactory(),
                null,
                null
            )
            exoPlayer.prepare(mediaSource)
        }
    }


}