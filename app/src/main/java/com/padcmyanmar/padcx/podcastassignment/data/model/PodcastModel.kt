package com.padcmyanmar.padcx.podcastassignment.data.model

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
interface PodcastModel {
    fun getRandomPodcastAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getCategoryListAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getPlayListAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getRandomPodcast() : LiveData<RandomPodcastVO>

    fun getCategoryList() : LiveData<List<CategoryVO>>

    fun getPlayListPodcasts() : LiveData<List<ItemVO>>

    fun getDetailsPodcasts(id: Int) : LiveData<ItemVO>

    fun getDownloadsPodcasts() : LiveData<List<ItemVO>>

    fun saveDownloadedItems(data: ItemVO)

}