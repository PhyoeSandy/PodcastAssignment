package com.padcmyanmar.padcx.podcastassignment.mvp.views

import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
interface SearchView {
    fun displayCategoryList(categories: List<CategoryVO>)
}