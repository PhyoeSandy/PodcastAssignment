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
import com.padcmyanmar.padcx.podcastassignment.adapters.CategoryAdapter
import com.padcmyanmar.padcx.podcastassignment.adapters.DownloadPodcastAdapter
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.DownloadPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.DownloadPresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.DownloadView
import com.padcmyanmar.padcx.podcastassignment.views.viewpods.EmptyViewPod
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_download.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DownloadFragment : BaseFragment(), DownloadView {
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DownloadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private lateinit var mAdapter: DownloadPodcastAdapter
    private lateinit var mCategoryAdapter: CategoryAdapter

    private lateinit var mPresenter: DownloadPresenter
    private lateinit var mViewPodEmpty: EmptyViewPod

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupViewPod()
        setupRecyclerView()

        mPresenter.onUiReady(this)
    }

    private fun setupViewPod() {
        mViewPodEmpty = vpEmpty as EmptyViewPod
        mViewPodEmpty.setDelegate(mPresenter)
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProviders.of(this).get(DownloadPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setupRecyclerView() {
        mAdapter = DownloadPodcastAdapter(mPresenter)
        with(rvDownloads) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

       /* mCategoryAdapter = CategoryAdapter()
        with(rvCategory) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mCategoryAdapter
        }*/
    }

    override fun displayDownloadPodcastList(downloads: List<DataVO>) {
        mAdapter.setNewData(downloads.toMutableList())
    }

    override fun displayCategoryList(categories: List<CategoryVO>) {
        mCategoryAdapter.setNewData(categories.toMutableList())
    }

    override fun navigateToPodcastDetails(podcastId: String) {
        startActivity(PodCastDetailsActivity.newIntent(requireContext(), podcastId))
    }

    override fun showErrorMessage(error: String) {
        Log.e("Download Fragment: ", error)
    }
}