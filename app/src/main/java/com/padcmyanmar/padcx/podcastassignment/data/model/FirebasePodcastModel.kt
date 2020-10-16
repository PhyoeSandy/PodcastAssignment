package com.padcmyanmar.padcx.podcastassignment.data.model

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
interface FirebasePodcastModel {

    fun getCategoryList(onSuccess: (categoryList: List<CategoryVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPlayListPodcasts(onSuccess: (itemList: List<DataVO>) -> Unit, onFailure: (String) -> Unit)

    fun getRandomPodcast() : LiveData<DataVO>

    fun getCategoryList() : LiveData<List<CategoryVO>>

    fun getPlayListPodcasts() : LiveData<List<DataVO>>

    fun getDetailsPodcasts(id: String) : LiveData<DataVO>

    fun getDownloadsPodcasts() : LiveData<List<DataVO>>

    fun saveDownloadedItems(data: DataVO)

}