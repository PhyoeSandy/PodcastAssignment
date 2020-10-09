package com.padcmyanmar.padcx.podcastassignment.data.model

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
interface FirebasePodcastModel {

    fun getRandomPodcast(onSuccess: (podcast: RandomPodcastVO) -> Unit, onFailure: (String) -> Unit)

    fun getCategoryList(onSuccess: (categoryList: List<CategoryVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPlayListPodcasts(onSuccess: (itemList: List<ItemVO>) -> Unit, onFailure: (String) -> Unit)

    // same as PodcastModel
    fun getRandomPodcast() : LiveData<RandomPodcastVO>

    fun getCategoryList() : LiveData<List<CategoryVO>>

    fun getPlayListPodcasts() : LiveData<List<ItemVO>>

    fun getDetailsPodcasts(id: Int) : LiveData<ItemVO>

    fun getDownloadsPodcasts() : LiveData<List<ItemVO>>

    fun saveDownloadedItems(data: ItemVO)

}