package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsResponse

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
@Dao
interface PlaylistPodcastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaylist(playlist: ItemVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlaylists(playlists: List<ItemVO>)

    @Query("SELECT * FROM playlist")
    fun getAllPlaylists(): LiveData<List<ItemVO>>

    @Query("SELECT * FROM playlist WHERE status =1")
    fun getAllDownloadPlaylists(): LiveData<List<ItemVO>>

    @Query("SELECT * FROM playlist WHERE id=:id")
    fun getPlaylistById(id: Int): LiveData<ItemVO>

    @Query("UPDATE playlist SET status =1 WHERE id=:id")
    fun updateStatusDownload(id: Int)

}