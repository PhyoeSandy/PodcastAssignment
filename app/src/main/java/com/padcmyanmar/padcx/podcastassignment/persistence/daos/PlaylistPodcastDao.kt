package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO

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

}