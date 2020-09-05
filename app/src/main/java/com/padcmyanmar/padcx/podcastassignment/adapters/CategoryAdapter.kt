package com.padcmyanmar.padcx.podcastassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcmyanmar.padcx.podcastassignment.R
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.views.viewholders.CategoryViewHolder
import com.padcmyanmar.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
class CategoryAdapter : BaseRecyclerAdapter<CategoryViewHolder, CategoryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }
}