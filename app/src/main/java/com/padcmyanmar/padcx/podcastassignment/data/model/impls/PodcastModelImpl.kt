package com.padcmyanmar.padcx.podcastassignment.data.model.impls

import androidx.lifecycle.LiveData
import com.padcmyanmar.padcx.podcastassignment.data.model.BaseModel
import com.padcmyanmar.padcx.podcastassignment.data.model.PodcastModel
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.podcastassignment.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
object PodcastModelImpl : PodcastModel, BaseModel() {

    override fun getRandomPodcastAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mPodcastApi.getRandomPodcast(PARAM_API_VALUE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mDB.podcastDao().insertPodcast(it)
            }, {
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })
    }

    override fun getCategoryListAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mPodcastApi.getCategoryList(PARAM_API_VALUE, 0)
            .map { it.genres?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mDB.categoryDao().insertCategory(it)
            }, {
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })
    }

    override fun getPlayListAndSaveToDb(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mPodcastApi.getPlayListPodcasts(
            PARAM_API_VALUE, "m1pe7z60bsw",
            TYPE_VALUE, PAGINATION_VALUE,
            SORT_VALUE
        )
            .map { it.items?.toList() ?: listOf() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mDB.playlistDao().insertAllPlaylists(it)
            }, {
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })
    }

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