package com.padcmyanmar.padcx.podcastassignment.data.model

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsResponse
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastResponse

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
interface PodcastModel {
    fun getRandomPodcastAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getCategoryListAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getPlayListAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getDetailsAndSaveToDb(id: String, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getRandomPodcast() : LiveData<RandomPodcastResponse>

    fun getCategoryList() : LiveData<List<CategoryVO>>

    fun getPlayListPodcasts() : LiveData<List<ItemVO>>

    fun getDetailsPodcasts(id: String) : LiveData<DetailsResponse>


}