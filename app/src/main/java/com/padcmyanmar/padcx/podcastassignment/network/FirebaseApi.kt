package com.padcmyanmar.padcx.podcastassignment.network

import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
interface FirebaseApi {

    fun getRandomPodcast(onSuccess: (podcast: RandomPodcastVO) -> Unit, onFailure: (String) -> Unit)

    fun getCategoryList(onSuccess: (categoryList: List<CategoryVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPlayListPodcasts(onSuccess: (itemList: List<ItemVO>) -> Unit, onFailure: (String) -> Unit)
}