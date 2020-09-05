package com.padcmyanmar.padcx.podcastassignment.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastResponse
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.CategoryDao
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.DetailsDao
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.PlaylistPodcastDao
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.RandomPodcastDao

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
@Database(entities = [PodcastVO::class, CategoryVO::class,
    DataVO::class, ItemVO::class,
    RandomPodcastResponse::class], version = 11, exportSchema = false)
abstract class PodcastDB : RoomDatabase() {
    companion object {
        val DB_NAME = "Podcast.DB"
        var dbInstance: PodcastDB? = null

        fun getDBInstance(context: Context): PodcastDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, PodcastDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun podcastDao() : RandomPodcastDao
    abstract fun categoryDao() : CategoryDao
    abstract fun playlistDao() : PlaylistPodcastDao
    abstract fun detailsDao() : DetailsDao

}