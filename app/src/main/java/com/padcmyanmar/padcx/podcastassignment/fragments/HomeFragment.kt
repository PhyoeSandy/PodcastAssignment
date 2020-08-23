package com.padcmyanmar.padcx.podcastassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.activities.PodCastDetailsActivity
import com.padcmyanmar.padcx.podcastassignment.adapters.PodCastAdapter
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.mvp.views.HomeView
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mPodCastAdapter = PodCastAdapter()
        with(rvPodcasts) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mPodCastAdapter
        }
    }

    override fun displayPodcastList(podcasts: List<PodcastVO>) {

    }

    override fun navigateToPodcastDetails() {
        startActivity(PodCastDetailsActivity.newIntent(requireContext()))
    }

}