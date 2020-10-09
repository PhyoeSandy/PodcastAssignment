package com.padcmyanmar.padcx.podcastassignment.data.model.impls

import androidx.lifecycle.LiveData
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
object FirebasePodcastModelImpl : FirebasePodcastModel, BaseModel() {
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




    // same as PodcastModel
    override fun getRandomPodcast(): LiveData<RandomPodcastVO> {
        return mDB.podcastDao().getRandomPodcast()
    }

    override fun getCategoryList(): LiveData<List<CategoryVO>> {
        return mDB.categoryDao().getAllCategories()
    }

    override fun getPlayListPodcasts(): LiveData<List<ItemVO>> {
        return mDB.playlistDao().getAllPlaylists()
    }

    override fun getDetailsPodcasts(id: Int): LiveData<ItemVO> {
        return mDB.playlistDao().getPlaylistById(id)
    }

    override fun getDownloadsPodcasts(): LiveData<List<ItemVO>> {
        return mDB.playlistDao().getAllDownloadPlaylists()
    }

    override fun saveDownloadedItems(data: ItemVO) {
        mDB.playlistDao().updateStatusDownload(data.id)
    }
}