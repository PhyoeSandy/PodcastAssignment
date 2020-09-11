package com.padcmyanmar.padcx.podcastassignment.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO

@Entity(tableName = "random_podcast")
data class RandomPodcastVO(

    @PrimaryKey
    @SerializedName("id")  val id: String,
    @SerializedName("audio") val audio: String,
    @SerializedName("audio_length_sec")  val audio_length_sec: Int,
    @SerializedName("description")  val description: String,
    @SerializedName("explicit_content")  val explicit_content: Boolean,
    @SerializedName("image")  val image: String,

    @SerializedName("podcast") val podcast: PodcastVO
)