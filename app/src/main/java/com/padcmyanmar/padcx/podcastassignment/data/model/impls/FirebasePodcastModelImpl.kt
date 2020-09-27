package com.padcmyanmar.padcx.podcastassignment.data.model.impls

import com.padcmyanmar.padcx.podcastassignment.data.model.BaseModel
import com.padcmyanmar.padcx.podcastassignment.data.model.FirebasePodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.FirebaseApi
import com.padcmyanmar.padcx.podcastassignment.network.impl.RealtimeDatabaseFirebaseApiImpl
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
class FirebasePodcastModelImpl : FirebasePodcastModel, BaseModel() {
    var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl

    override fun getRandomPodcast(
        onSuccess: (podcast: RandomPodcastVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getRandomPodcast({
            mDB.podcastDao().insertPodcast(it)
        },{ onFailure(it) })
    }

    override fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCategoryList({
            mDB.categoryDao().insertCategory(it)
        }, { onFailure(it) })
    }

    override fun getPlayListPodcasts(
        onSuccess: (itemList: List<ItemVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
       mFirebaseApi.getPlayListPodcasts({
           mDB.playlistDao().insertAllPlaylists(it)
       }, { onFailure(it) })
    }
}