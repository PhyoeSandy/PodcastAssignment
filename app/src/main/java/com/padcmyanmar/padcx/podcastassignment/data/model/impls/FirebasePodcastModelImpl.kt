package com.padcmyanmar.padcx.podcastassignment.data.model.impls

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.podcastassignment.data.model.BaseModel
import com.padcmyanmar.padcx.podcastassignment.data.model.FirebasePodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.network.FirebaseApi
import com.padcmyanmar.padcx.podcastassignment.network.impl.CloudFirestoreApiImpl
import com.padcmyanmar.padcx.podcastassignment.network.impl.RealtimeDatabaseFirebaseApiImpl

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
object FirebasePodcastModelImpl : FirebasePodcastModel, BaseModel() {
    //var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl
    var mFirebaseApi: FirebaseApi = CloudFirestoreApiImpl

    override fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCategoryList({
            mDB.categoryDao().deleteAllCategories()
            mDB.categoryDao().insertCategory(it)
        }, { onFailure(it) })
    }

    override fun getPlayListPodcasts(
        onSuccess: (itemList: List<DataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
       mFirebaseApi.getPlayListPodcasts({
           mDB.episodeDao().deleteAllPlaylists()
           mDB.episodeDao().insertAllPlaylists(it)
       }, { onFailure(it) })
    }

    override fun getCategoryList(): LiveData<List<CategoryVO>> {
        return mDB.categoryDao().getAllCategories()
    }

    override fun getPlayListPodcasts(): LiveData<List<DataVO>> {
        return mDB.episodeDao().getAllPlaylists()
    }

    override fun getRandomPodcast(): LiveData<DataVO> {
        return mDB.episodeDao().getRandomPlaylist()
    }

    override fun getDetailsPodcasts(id: String): LiveData<DataVO> {
        return mDB.episodeDao().getPlaylistById(id)
    }

    override fun getDownloadsPodcasts(): LiveData<List<DataVO>> {
        return mDB.episodeDao().getAllDownloadPlaylists()
    }

    override fun saveDownloadedItems(data: DataVO) {
        mDB.episodeDao().updateStatusDownload(data.id)
    }
}