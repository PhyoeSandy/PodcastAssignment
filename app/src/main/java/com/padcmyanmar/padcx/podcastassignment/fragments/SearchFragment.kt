package com.padcmyanmar.padcx.podcastassignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.adapters.CategoryAdapter
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.SearchPresenter
import com.padcmyanmar.padcx.podcastassignment.mvp.presenters.impl.SearchPresenterImpl
import com.padcmyanmar.padcx.podcastassignment.mvp.views.SearchView
import com.padcmyanmar.padcx.shared.extensions.loadImage
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_category.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : BaseFragment(), SearchView {
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mPresenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupPresenter()
        setupRecyclerView()

        mPresenter.onUiReady(this)
    }

    private fun setupPresenter() {
        mPresenter = ViewModelProviders.of(this)
            .get(SearchPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setupRecyclerView() {
        mCategoryAdapter = CategoryAdapter()
        with(rvCategory) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mCategoryAdapter
        }
    }

    override fun displayCategoryList(categories: List<CategoryVO>) {
        mCategoryAdapter.setNewData(categories.toMutableList())
    }

    override fun bindData(category: CategoryVO) {
        tvCategory.text = category.name
        ivImage.loadImage(category.image)
    }

    override fun showErrorMessage(error: String) {
        Log.e("Search Fragment: ", error)
    }

}