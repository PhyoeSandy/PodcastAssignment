package com.padcmyanmar.padcx.podcastassignment.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.PlaylistsVO
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.CategoryDao
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.DetailsDao
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.PlaylistPodcastDao
import com.padcmyanmar.padcx.podcastassignment.persistence.daos.RandomPodcastDao
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.DataTypeConverter
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.PodcastTypeConverter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
//ItemVO::class,
@Database(entities = [PodcastVO::class, CategoryVO::class,
    DataVO::class, PlaylistsVO::class,
    RandomPodcastVO::class,
    DetailsVO::class], version = 14, exportSchema = false)
@TypeConverters(PodcastTypeConverter::class, DataTypeConverter::class)
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