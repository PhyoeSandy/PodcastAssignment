package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.shared.mvp.views.BaseView

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface SearchView: BaseView {
    fun displayCategoryList(categories: List<CategoryVO>)
    fun bindCategoryName(category: CategoryVO)
}