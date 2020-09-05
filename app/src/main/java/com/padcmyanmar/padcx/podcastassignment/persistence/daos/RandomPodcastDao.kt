package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
@Dao
interface RandomPodcastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodcast(podcast: RandomPodcastVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPodcasts(podcasts: List<RandomPodcastVO>)

    @Query("SELECT * FROM random_podcast")
    fun getRandomPodcast() : LiveData<RandomPodcastVO>

}