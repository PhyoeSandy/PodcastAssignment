package com.padcmyanmar.padcx.podcastassignment.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
@Entity(tableName = "details")
data class DetailsResponse(
    @SerializedName("audio")var audio: String,
    @SerializedName("audio_length_sec")var audio_length_sec: Int,
    @SerializedName("description")var description: String,
    @SerializedName("explicit_content")var explicit_content: Boolean,

    @PrimaryKey
    @SerializedName("id")var id: String,
    @SerializedName("image")var image: String,
    @SerializedName("link")var link: String,
    @SerializedName("podcast")var podcast: PodcastVO


)