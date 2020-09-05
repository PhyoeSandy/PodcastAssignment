package com.padcmyanmar.padcx.podcastassignment.views.viewholders

import android.view.View
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
class CategoryViewHolder(itemView: View) : BaseViewHolder<CategoryVO>(itemView) {
    override fun bindData(data: CategoryVO) {
        mData = data

        itemView.tvCategoryName.text = data.name
    }
}