package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastResponse

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
@Dao
interface RandomPodcastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodcast(podcast: RandomPodcastResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPodcasts(podcasts: List<RandomPodcastResponse>)

    @Query("SELECT * FROM random_podcast")
    fun getRandomPodcast() : LiveData<RandomPodcastResponse>

}