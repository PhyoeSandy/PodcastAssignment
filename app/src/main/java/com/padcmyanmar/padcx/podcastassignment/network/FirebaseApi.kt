package com.padcmyanmar.padcx.podcastassignment.network

import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
interface FirebaseApi {

    fun getCategoryList(onSuccess: (categoryList: List<CategoryVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPlayListPodcasts(onSuccess: (itemList: List<DataVO>) -> Unit, onFailure: (String) -> Unit)
}