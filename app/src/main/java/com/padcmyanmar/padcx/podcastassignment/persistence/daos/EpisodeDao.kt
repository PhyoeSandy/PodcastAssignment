package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import retrofit2.http.DELETE

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/10/2020.
 */
@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaylist(playlist: DataVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlaylists(playlists: List<DataVO>)

    @Query("SELECT * FROM episode_data")
    fun getAllPlaylists(): LiveData<List<DataVO>>

    @Query("SELECT * FROM episode_data WHERE status =1")
    fun getAllDownloadPlaylists(): LiveData<List<DataVO>>

    @Query("SELECT * FROM episode_data ORDER BY RANDOM()")
    fun getRandomPlaylist(): LiveData<DataVO>

    @Query("SELECT * FROM episode_data WHERE id=:id")
    fun getPlaylistById(id: String): LiveData<DataVO>

    @Query("UPDATE episode_data SET status =1 WHERE id=:id")
    fun updateStatusDownload(id: String)

    @Query("DELETE FROM episode_data")
    fun deleteAllPlaylists()

}