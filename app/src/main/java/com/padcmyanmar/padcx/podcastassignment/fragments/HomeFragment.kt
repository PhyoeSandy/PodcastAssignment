package com.padcmyanmar.padcx.podcastassignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.activities.PodCastDetailsActivity
import com.padcmyanmar.padcx.podcastassignment.adapters.PodCastAdapter
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.HomePresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.HomePresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.EmptyViewPod
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.PlayerViewPod
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment(), HomeView {
    companion object {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupPresenter()
        setupRecyclerView()
        setupViewPod()

        mPresenter.onUiReady(this)
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

    override fun displayRandomPodcast(podcast: PodcastVO) {
        mPlayerViewPod.setData(podcast)
    }

    override fun bindDescription(description: String) {
        tvDetails.text = description
    }

    override fun navigateToPodcastDetails(podcastId: String) {
        startActivity(activity?.let {
            PodCastDetailsActivity.newIntent(it,podcastId)
        })
    }

    override fun showErrorMessage(error: String) {
        Log.e("Home Fragment: ", error)
    }

}