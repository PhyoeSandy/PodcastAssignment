package com.padcmyanmar.padcx.podcastassignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.adapters.PodCastAdapter
import com.padcmyanmar.padcx.shared.fragments.BaseFragment

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DownloadFragment : BaseFragment(){
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

    private lateinit var mPodCastAdapter :PodCastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_download, container, false)    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mPodCastAdapter = PodCastAdapter()
       /* with() {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mPodCastAdapter
        }*/
    }
}