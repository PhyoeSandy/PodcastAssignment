package com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/2/2020.
 */
class PodcastTypeConverter {

    @TypeConverter
    fun toString(podcast: PodcastVO) : String {
        return Gson().toJson(podcast)
    }

    @TypeConverter
    fun toPodcast(podcastJsonString: String) : PodcastVO {
        val podcastType = object : TypeToken<PodcastVO>() {}.type
        return Gson().fromJson(podcastJsonString,podcastType)
    }

}