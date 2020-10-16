package com.padcmyanmar.padcx.podcastassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
@IgnoreExtraProperties
@Entity(tableName = "episode_data")
data class DataVO(
    @PrimaryKey
    @SerializedName("id") var id: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("podcast") var podcast: PodcastVO? = null,
    @SerializedName("audio") var audio: String = "",
    @SerializedName("audio_length_sec") var audio_length_sec: Int = 0,
    @SerializedName("description") var description: String = "",
    @SerializedName("title") var title: String = "",
    var status: Int = 0 // 1 for download
)