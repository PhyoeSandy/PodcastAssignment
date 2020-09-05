package com.padcmyanmar.padcx.podcastassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.PodcastTypeConverter

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
@Entity(tableName = "episode_data")
data class DataVO(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String,
    @SerializedName("podcast") val podcast: PodcastVO,
    @SerializedName("audio") val audio: String,
    @SerializedName("audio_length_sec") val audio_length_sec: Int,
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String

/*
 @SerializedName("explicit_content") val explicit_content: Boolean,
 val description: String,
 val last_timestamp_ms: Long,
 val listennotes_url: String,
 val name: String,
 val thumbnail: String,
 val total: Int,
 val type: String,
 val visibility: String*/
)